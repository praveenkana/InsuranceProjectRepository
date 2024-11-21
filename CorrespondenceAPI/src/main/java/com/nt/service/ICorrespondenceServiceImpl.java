package com.nt.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.bindings.CmnSummary;
import com.nt.entity.AppRegistrationEntity;
import com.nt.entity.CmnEntity;
import com.nt.entity.DC_CaseEntity;
import com.nt.entity.EligibilityDetailsEntity;
import com.nt.repository.IAppRegistrationRepository;
import com.nt.repository.IComnRepository;
import com.nt.repository.IDCCaseRepository;
import com.nt.repository.IEligibilityDeterminationRepository;
import com.nt.utils.EmailUtils;

import jakarta.mail.MessagingException;

@Service
public class ICorrespondenceServiceImpl implements ICorrespondenceService {
	@Autowired
	private IComnRepository commrepo;
	@Autowired
	private IEligibilityDeterminationRepository elgirepo;
	@Autowired
	private IDCCaseRepository caserepo;
	@Autowired
	private IAppRegistrationRepository apprepo;

	private EmailUtils utils;

	@Override
	public CmnSummary processPendingCommunication() throws MessagingException, IOException {

		AppRegistrationEntity appEntity = null;
		EligibilityDetailsEntity elgiEntity = null;
		List<CmnEntity> cmnList = commrepo.findByComnStatus("pending");

		int successComns = 0;
		for (CmnEntity trigger : cmnList) {
			elgiEntity = elgirepo.findByCaseNo(trigger.getCaseNo());
			Optional<DC_CaseEntity> opt = caserepo.findById(trigger.getCaseNo());
			if (opt.isPresent()) {
				DC_CaseEntity caseEntity = opt.get();
				Integer appId = caseEntity.getAppId();
				Optional<AppRegistrationEntity> opt1 = apprepo.findById(appId);
				if (opt1.isPresent()) {
					appEntity = opt1.get();
				}

				generatePdfAndSendMail(elgiEntity, appEntity);
				successComns++;
			}
		}

		CmnSummary summary = new CmnSummary();
		summary.setTotalComns(cmnList.size());
		summary.setSuccessComns(successComns);
		return summary;
	}

	// helper metod to generate pdf

	private void generatePdfAndSendMail(EligibilityDetailsEntity elgiEntity, AppRegistrationEntity appEntity)
			throws MessagingException, IOException {

		// create document object
		Document document = new Document(PageSize.A4);

		// create pdf file
		File file = new File(elgiEntity.getCaseNo() + ".pdf");

		FileOutputStream fos = new FileOutputStream(file);
		// get pdf writer

		PdfWriter.getInstance(document, fos);
		// open the document
		document.open();
		// define the font charecterisics

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.CYAN);
		font.setSize(30);

		// create a paragraph

		Paragraph para = new Paragraph("plan approval/denial communication", font);
		para.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(para);

		// dispal results as pdf table

		PdfPTable table = new PdfPTable(10);
		table.setWidthPercentage(70);
		table.setWidths(new float[] { 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f, 3.0f });
		table.setSpacingBefore(2);

		// prepare head row cells

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.gray);
		cell.setPadding(5);
		Font cellFont = FontFactory.getFont(FontFactory.HELVETICA);
		cellFont.setColor(Color.BLUE);

		cell.setPhrase(new Phrase("ElgibilityId", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("caseNo", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("holderName", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("holderIdValue", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planName", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planStatus", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planStartDate", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("planEndDate", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("benefitAmount", cellFont));
		table.addCell(cell);

		cell.setPhrase(new Phrase("denialReason", cellFont));
		table.addCell(cell);

		// add data to table
		table.addCell(String.valueOf(elgiEntity.getElgibilityId()));
		table.addCell(String.valueOf(elgiEntity.getCaseNo()));
		table.addCell(elgiEntity.getHolderName());
		table.addCell(String.valueOf(elgiEntity.getHolderIdValue()));
		table.addCell(elgiEntity.getPlanName());
		table.addCell(elgiEntity.getPlanStatus());
		table.addCell(String.valueOf(elgiEntity.getPlanStartDate()));
		table.addCell(String.valueOf(elgiEntity.getPlanEndDate()));
		table.addCell(String.valueOf(elgiEntity.getBenefitAmount()));
		table.addCell(elgiEntity.getDenialReason());

		document.add(table);
		document.close();

		// send generated pdf document as email message
		String message = "PLAN APPROVAL/DENIAL MAIL";
		String body = "hello " + appEntity.getFullName()
				+ "this mail contains completete details about your plan approval";
		utils.sendMail(appEntity.getEmal(), message, body, file);
		updateCOTrigger(elgiEntity.getCaseNo(), file);
	}

	private void updateCOTrigger(Integer caseNo, File file) throws IOException {

		CmnEntity cmnEntity = commrepo.findByCaseNo(caseNo);
		// prepare byte[]representing pdf doc

		byte[] pdfDoc = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(pdfDoc);
		if (cmnEntity != null) {
			cmnEntity.setComnNoticepdf(pdfDoc);
			cmnEntity.setComnStatus("completed");
			commrepo.save(cmnEntity);
		}
		fis.close();
	}

}
