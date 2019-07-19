package edu.mum.rentalHouse.serviceImpl;

import edu.mum.rentalHouse.model.Tenant;
import edu.mum.rentalHouse.repository.TenantRepository;
import edu.mum.rentalHouse.service.TenantService;
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

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    private TenantRepository tenantRepository;
    @Override
    public void createTenant( Tenant tenant ) {
          tenantRepository.save(tenant);
    }

    @Override
    public void updateTenant(Tenant tenant) {
        tenantRepository.save(tenant);

    }

    @Override
    public Tenant getTenant(Long id) {
        return tenantRepository.getOne(id);
    }

    @Override
    public void deleteTenant(Tenant tenant) {
        tenantRepository.delete(tenant);

    }

    @Override
    public List<Tenant> getAllTenant() {
        return tenantRepository.findAll();
    }

	@Override
    public void delete(Long tenantId) {
		// TODO Auto-generated method stub
		tenantRepository.deleteById(tenantId);
	}

	@Override
	public boolean createPDF(List<Tenant> tenants, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			Document document=new Document(PageSize.A4, 15,15,15,15);

			String path=context.getRealPath("/resources/reports");
			File file=new File(path);
			boolean exists=new File(path).exists(); 
			if(!exists) new File(path).mkdirs();

			PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file+"/"+"tenants"+".pdf"));
			document.open();

			Font font=FontFactory.getFont("Arial",10,BaseColor.BLACK);
			Paragraph paragraph=new Paragraph("List of Tenants",font);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);


			PdfPTable table=new PdfPTable(4);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font headerFont=FontFactory.getFont("Arial",10, BaseColor.BLACK);
			Font bodyFont=FontFactory.getFont("Arial",9, BaseColor.BLACK);

			float []columnWidth= {2f,2f,2f,2f};
			table.setWidths(columnWidth);

			PdfPCell nomTenant=new PdfPCell(new Paragraph("Last Name", headerFont));
			nomTenant.setBorderColor(BaseColor.BLACK);
			nomTenant.setPaddingLeft(10);
			nomTenant.setHorizontalAlignment(Element.ALIGN_CENTER);
			nomTenant.setVerticalAlignment(Element.ALIGN_CENTER);
			nomTenant.setBackgroundColor(BaseColor.GRAY);
			nomTenant.setExtraParagraphSpace(5f);
			table.addCell(nomTenant);

			PdfPCell prennomTenant=new PdfPCell(new Paragraph("First Name", headerFont));
			prennomTenant.setBorderColor(BaseColor.BLACK);
			prennomTenant.setPaddingLeft(10);
			prennomTenant.setHorizontalAlignment(Element.ALIGN_CENTER);
			prennomTenant.setVerticalAlignment(Element.ALIGN_CENTER);
			prennomTenant.setBackgroundColor(BaseColor.GRAY);
			prennomTenant.setExtraParagraphSpace(5f);
			table.addCell(prennomTenant);


			PdfPCell genderTenant=new PdfPCell(new Paragraph("Gender", headerFont));
			genderTenant.setBorderColor(BaseColor.BLACK);
			genderTenant.setPaddingLeft(10);
			genderTenant.setHorizontalAlignment(Element.ALIGN_CENTER);
			genderTenant.setVerticalAlignment(Element.ALIGN_CENTER);
			genderTenant.setBackgroundColor(BaseColor.GRAY);
			genderTenant.setExtraParagraphSpace(5f);
			table.addCell(genderTenant);


			PdfPCell ageTenant=new PdfPCell(new Paragraph("Age", headerFont));
			ageTenant.setBorderColor(BaseColor.BLACK);
			ageTenant.setPaddingLeft(10);
			ageTenant.setHorizontalAlignment(Element.ALIGN_CENTER);
			ageTenant.setVerticalAlignment(Element.ALIGN_CENTER);
			ageTenant.setBackgroundColor(BaseColor.GRAY);
			ageTenant.setExtraParagraphSpace(5f);
			table.addCell(ageTenant);

			for(Tenant tenant:tenants) {
				PdfPCell nomValue=new PdfPCell(new Paragraph(tenant.getLastName(), bodyFont));
				nomValue.setBorderColor(BaseColor.BLACK);
				nomValue.setPaddingLeft(10);
				nomValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				nomValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	nomValue.setBackgroundColor(BaseColor.GRAY);
				nomValue.setExtraParagraphSpace(5f);
				table.addCell(nomValue);

				PdfPCell prenomValue=new PdfPCell(new Paragraph(tenant.getFirstName(), bodyFont));
				prenomValue.setBorderColor(BaseColor.BLACK);
				prenomValue.setPaddingLeft(10);
				prenomValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				prenomValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	descriptionValue.setBackgroundColor(BaseColor.GRAY);
				prenomValue.setExtraParagraphSpace(5f);
				table.addCell(prenomValue);


				PdfPCell genderValue=new PdfPCell(new Paragraph(tenant.getGender(), bodyFont));
				genderValue.setBorderColor(BaseColor.BLACK);
				genderValue.setPaddingLeft(10);
				genderValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				genderValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	prixValue.setBackgroundColor(BaseColor.GRAY);
				genderValue.setExtraParagraphSpace(5f);
				table.addCell(genderValue);


				PdfPCell ageValue=new PdfPCell(new Paragraph(""+tenant.getAge(), bodyFont));
				ageValue.setBorderColor(BaseColor.BLACK);
				ageValue.setPaddingLeft(10);
				ageValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ageValue.setVerticalAlignment(Element.ALIGN_CENTER);
				//	quantiteValue.setBackgroundColor(BaseColor.GRAY);
				ageValue.setExtraParagraphSpace(5f);
				table.addCell(ageValue);

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
