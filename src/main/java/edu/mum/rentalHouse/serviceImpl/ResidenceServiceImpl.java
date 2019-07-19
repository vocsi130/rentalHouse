package edu.mum.rentalHouse.serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import edu.mum.rentalHouse.model.Apartment;
import edu.mum.rentalHouse.model.House;
import edu.mum.rentalHouse.model.Residence;
import edu.mum.rentalHouse.model.Tenant;
import edu.mum.rentalHouse.repository.ResidenceRepository;
import edu.mum.rentalHouse.service.ResidenceService;

@Service
@Transactional
public class ResidenceServiceImpl implements ResidenceService {

	@Autowired
	private ResidenceRepository residenceRepository;
	
	@Override
	public void saveResidence(Residence residence) {
		// TODO Auto-generated method stub
		
		residenceRepository.save(residence);
	}

	@Override
	public void updateResidence(Residence residence) {
		// TODO Auto-generated method stub
		residenceRepository.save(residence);
	}

	@Override
	public void deleteResidence(Long id) {
		// TODO Auto-generated method stub
		residenceRepository.deleteById(id);
	}

	@Override
	public Residence get(Long id) {
		// TODO Auto-generated method stub
		return residenceRepository.findById(id).get();
	}

	@Override
	public List<Residence> getAll() {
		// TODO Auto-generated method stub
		return residenceRepository.findAll();
	}

	@Override
	public List<Residence> getHouses() {
		// TODO Auto-generated method stub
		return residenceRepository.getHouses();
	}

	@Override
	public List<Residence> getApartments() {
		// TODO Auto-generated method stub
		return residenceRepository.getApartments();
	}

	public void deleteHouse(Long id) {
		// TODO Auto-generated method stub
		residenceRepository.deleteById(id);
	}

	public void deleteApartment(Long id) {
		// TODO Auto-generated method stub
		residenceRepository.deleteById(id);
		
	}

	@Override
	public boolean createPDFHouse(List<House> houses, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Document document=new Document(PageSize.A4, 15,15,15,15);

			String path=context.getRealPath("/resources/reports");
			File file=new File(path);
			boolean exists=new File(path).exists(); 
			if(!exists) new File(path).mkdirs();

			PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file+"/"+"houses"+".pdf"));
			document.open();

			Font font=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Paragraph paragraph=new Paragraph("List of Houses",font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);


			PdfPTable table=new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font headerFont=FontFactory.getFont("Arial",10, BaseColor.BLACK);
			Font bodyFont=FontFactory.getFont("Arial",9, BaseColor.BLACK);

			float []columnWidth= {3f,3f,2f,1.5f,1.5f,1.5f,2f};
			table.setWidths(columnWidth);

			PdfPCell street=new PdfPCell(new Paragraph("Street", headerFont));
			street.setBorderColor(BaseColor.BLACK);
			street.setPaddingLeft(10);
			street.setHorizontalAlignment(Element.ALIGN_CENTER);
			street.setVerticalAlignment(Element.ALIGN_CENTER);
			street.setBackgroundColor(BaseColor.GRAY);
			street.setExtraParagraphSpace(5f);
			table.addCell(street);

			PdfPCell city=new PdfPCell(new Paragraph("City", headerFont));
			city.setBorderColor(BaseColor.BLACK);
			city.setPaddingLeft(10);
			city.setHorizontalAlignment(Element.ALIGN_CENTER);
			city.setVerticalAlignment(Element.ALIGN_CENTER);
			city.setBackgroundColor(BaseColor.GRAY);
			city.setExtraParagraphSpace(5f);
			table.addCell(city);


			PdfPCell state=new PdfPCell(new Paragraph("State", headerFont));
			state.setBorderColor(BaseColor.BLACK);
			state.setPaddingLeft(10);
			state.setHorizontalAlignment(Element.ALIGN_CENTER);
			state.setVerticalAlignment(Element.ALIGN_CENTER);
			state.setBackgroundColor(BaseColor.GRAY);
			state.setExtraParagraphSpace(5f);
			table.addCell(state);


			PdfPCell zip=new PdfPCell(new Paragraph("Zip Code", headerFont));
			zip.setBorderColor(BaseColor.BLACK);
			zip.setPaddingLeft(10);
			zip.setHorizontalAlignment(Element.ALIGN_CENTER);
			zip.setVerticalAlignment(Element.ALIGN_CENTER);
			zip.setBackgroundColor(BaseColor.GRAY);
			zip.setExtraParagraphSpace(5f);
			table.addCell(zip);

			PdfPCell square=new PdfPCell(new Paragraph("Square", headerFont));
			square.setBorderColor(BaseColor.BLACK);
			square.setPaddingLeft(10);
			square.setHorizontalAlignment(Element.ALIGN_CENTER);
			square.setVerticalAlignment(Element.ALIGN_CENTER);
			square.setBackgroundColor(BaseColor.GRAY);
			square.setExtraParagraphSpace(5f);
			table.addCell(square);

			PdfPCell lotSize=new PdfPCell(new Paragraph("Lot Size", headerFont));
			lotSize.setBorderColor(BaseColor.BLACK);
			lotSize.setPaddingLeft(10);
			lotSize.setHorizontalAlignment(Element.ALIGN_CENTER);
			lotSize.setVerticalAlignment(Element.ALIGN_CENTER);
			lotSize.setBackgroundColor(BaseColor.GRAY);
			lotSize.setExtraParagraphSpace(5f);
			table.addCell(lotSize);

			PdfPCell date=new PdfPCell(new Paragraph("Built Date", headerFont));
			date.setBorderColor(BaseColor.BLACK);
			date.setPaddingLeft(10);
			date.setHorizontalAlignment(Element.ALIGN_CENTER);
			date.setVerticalAlignment(Element.ALIGN_CENTER);
			date.setBackgroundColor(BaseColor.GRAY);
			date.setExtraParagraphSpace(5f);
			table.addCell(date);

			for(House house:houses) {
				PdfPCell streetValue=new PdfPCell(new Paragraph(house.getAddress().getStreet(), bodyFont));
				streetValue.setBorderColor(BaseColor.BLACK);
				streetValue.setPaddingLeft(10);
				streetValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				streetValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	nomValue.setBackgroundColor(BaseColor.GRAY);
				streetValue.setExtraParagraphSpace(5f);
				table.addCell(streetValue);

				PdfPCell cityValue=new PdfPCell(new Paragraph(house.getAddress().getCity(), bodyFont));
				cityValue.setBorderColor(BaseColor.BLACK);
				cityValue.setPaddingLeft(10);
				cityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				cityValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	descriptionValue.setBackgroundColor(BaseColor.GRAY);
				cityValue.setExtraParagraphSpace(5f);
				table.addCell(cityValue);


				PdfPCell stateValue=new PdfPCell(new Paragraph(house.getAddress().getState(), bodyFont));
				stateValue.setBorderColor(BaseColor.BLACK);
				stateValue.setPaddingLeft(10);
				stateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				stateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				stateValue.setExtraParagraphSpace(5f);
				table.addCell(stateValue);


				PdfPCell zipValue=new PdfPCell(new Paragraph(house.getAddress().getZip(), bodyFont));
				zipValue.setBorderColor(BaseColor.BLACK);
				zipValue.setPaddingLeft(10);
				zipValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				zipValue.setVerticalAlignment(Element.ALIGN_CENTER);
				zipValue.setExtraParagraphSpace(5f);
				table.addCell(zipValue);

				PdfPCell squareValue=new PdfPCell(new Paragraph(""+house.getSquareFt(), bodyFont));
				squareValue.setBorderColor(BaseColor.BLACK);
				squareValue.setPaddingLeft(10);
				squareValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				squareValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				squareValue.setExtraParagraphSpace(5f);
				table.addCell(squareValue);

				PdfPCell lotValue=new PdfPCell(new Paragraph(""+house.getLotSize(), bodyFont));
				lotValue.setBorderColor(BaseColor.BLACK);
				lotValue.setPaddingLeft(10);
				lotValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				lotValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				lotValue.setExtraParagraphSpace(5f);
				table.addCell(lotValue);

				PdfPCell dateValue=new PdfPCell(new Paragraph(""+house.getBuiltDate(), bodyFont));
				dateValue.setBorderColor(BaseColor.BLACK);
				dateValue.setPaddingLeft(10);
				dateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				dateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				dateValue.setExtraParagraphSpace(5f);
				table.addCell(dateValue);

			}
			document.add(table);
			document.close();
			pdfWriter.close();
			return true;
		}catch (Exception e) {
			return false;
		}	

		/*	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
	}

	@Override
	public boolean createPDFApartment(List<Apartment> apartments, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Document document=new Document(PageSize.A4, 15,15,15,15);

			String path=context.getRealPath("/resources/reports");
			File file=new File(path);
			boolean exists=new File(path).exists(); 
			if(!exists) new File(path).mkdirs();

			PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file+"/"+"apartments"+".pdf"));
			document.open();

			Font font=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Paragraph paragraph=new Paragraph("List of Apartments",font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);


			PdfPTable table=new PdfPTable(7);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font headerFont=FontFactory.getFont("Arial",10, BaseColor.BLACK);
			Font bodyFont=FontFactory.getFont("Arial",9, BaseColor.BLACK);

			float []columnWidth= {3f,3f,2f,1.5f,1.5f,1.5f,2f};
			table.setWidths(columnWidth);

			PdfPCell street=new PdfPCell(new Paragraph("Street", headerFont));
			street.setBorderColor(BaseColor.BLACK);
			street.setPaddingLeft(10);
			street.setHorizontalAlignment(Element.ALIGN_CENTER);
			street.setVerticalAlignment(Element.ALIGN_CENTER);
			street.setBackgroundColor(BaseColor.GRAY);
			street.setExtraParagraphSpace(5f);
			table.addCell(street);

			PdfPCell city=new PdfPCell(new Paragraph("City", headerFont));
			city.setBorderColor(BaseColor.BLACK);
			city.setPaddingLeft(10);
			city.setHorizontalAlignment(Element.ALIGN_CENTER);
			city.setVerticalAlignment(Element.ALIGN_CENTER);
			city.setBackgroundColor(BaseColor.GRAY);
			city.setExtraParagraphSpace(5f);
			table.addCell(city);


			PdfPCell state=new PdfPCell(new Paragraph("State", headerFont));
			state.setBorderColor(BaseColor.BLACK);
			state.setPaddingLeft(10);
			state.setHorizontalAlignment(Element.ALIGN_CENTER);
			state.setVerticalAlignment(Element.ALIGN_CENTER);
			state.setBackgroundColor(BaseColor.GRAY);
			state.setExtraParagraphSpace(5f);
			table.addCell(state);


			PdfPCell zip=new PdfPCell(new Paragraph("Zip Code", headerFont));
			zip.setBorderColor(BaseColor.BLACK);
			zip.setPaddingLeft(10);
			zip.setHorizontalAlignment(Element.ALIGN_CENTER);
			zip.setVerticalAlignment(Element.ALIGN_CENTER);
			zip.setBackgroundColor(BaseColor.GRAY);
			zip.setExtraParagraphSpace(5f);
			table.addCell(zip);

			PdfPCell square=new PdfPCell(new Paragraph("Square", headerFont));
			square.setBorderColor(BaseColor.BLACK);
			square.setPaddingLeft(10);
			square.setHorizontalAlignment(Element.ALIGN_CENTER);
			square.setVerticalAlignment(Element.ALIGN_CENTER);
			square.setBackgroundColor(BaseColor.GRAY);
			square.setExtraParagraphSpace(5f);
			table.addCell(square);

			PdfPCell lotSize=new PdfPCell(new Paragraph("Floor", headerFont));
			lotSize.setBorderColor(BaseColor.BLACK);
			lotSize.setPaddingLeft(10);
			lotSize.setHorizontalAlignment(Element.ALIGN_CENTER);
			lotSize.setVerticalAlignment(Element.ALIGN_CENTER);
			lotSize.setBackgroundColor(BaseColor.GRAY);
			lotSize.setExtraParagraphSpace(5f);
			table.addCell(lotSize);

			PdfPCell date=new PdfPCell(new Paragraph("Built Date", headerFont));
			date.setBorderColor(BaseColor.BLACK);
			date.setPaddingLeft(10);
			date.setHorizontalAlignment(Element.ALIGN_CENTER);
			date.setVerticalAlignment(Element.ALIGN_CENTER);
			date.setBackgroundColor(BaseColor.GRAY);
			date.setExtraParagraphSpace(5f);
			table.addCell(date);

			for(Apartment apartment:apartments) {
				PdfPCell streetValue=new PdfPCell(new Paragraph(apartment.getAddress().getStreet(), bodyFont));
				streetValue.setBorderColor(BaseColor.BLACK);
				streetValue.setPaddingLeft(10);
				streetValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				streetValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	nomValue.setBackgroundColor(BaseColor.GRAY);
				streetValue.setExtraParagraphSpace(5f);
				table.addCell(streetValue);

				PdfPCell cityValue=new PdfPCell(new Paragraph(apartment.getAddress().getCity(), bodyFont));
				cityValue.setBorderColor(BaseColor.BLACK);
				cityValue.setPaddingLeft(10);
				cityValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				cityValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	descriptionValue.setBackgroundColor(BaseColor.GRAY);
				cityValue.setExtraParagraphSpace(5f);
				table.addCell(cityValue);


				PdfPCell stateValue=new PdfPCell(new Paragraph(apartment.getAddress().getState(), bodyFont));
				stateValue.setBorderColor(BaseColor.BLACK);
				stateValue.setPaddingLeft(10);
				stateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				stateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				stateValue.setExtraParagraphSpace(5f);
				table.addCell(stateValue);


				PdfPCell zipValue=new PdfPCell(new Paragraph(apartment.getAddress().getZip(), bodyFont));
				zipValue.setBorderColor(BaseColor.BLACK);
				zipValue.setPaddingLeft(10);
				zipValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				zipValue.setVerticalAlignment(Element.ALIGN_CENTER);
				zipValue.setExtraParagraphSpace(5f);
				table.addCell(zipValue);

				PdfPCell squareValue=new PdfPCell(new Paragraph(""+apartment.getSquareFt(), bodyFont));
				squareValue.setBorderColor(BaseColor.BLACK);
				squareValue.setPaddingLeft(10);
				squareValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				squareValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				squareValue.setExtraParagraphSpace(5f);
				table.addCell(squareValue);

				PdfPCell lotValue=new PdfPCell(new Paragraph(""+apartment.getFloor(), bodyFont));
				lotValue.setBorderColor(BaseColor.BLACK);
				lotValue.setPaddingLeft(10);
				lotValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				lotValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				lotValue.setExtraParagraphSpace(5f);
				table.addCell(lotValue);

				PdfPCell dateValue=new PdfPCell(new Paragraph(""+apartment.getBuiltDate(), bodyFont));
				dateValue.setBorderColor(BaseColor.BLACK);
				dateValue.setPaddingLeft(10);
				dateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				dateValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				dateValue.setExtraParagraphSpace(5f);
				table.addCell(dateValue);

			}
			document.add(table);
			document.close();
			pdfWriter.close();
			return true;
		}catch (Exception e) {
			return false;
		}	

		/*	} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
	}

}
