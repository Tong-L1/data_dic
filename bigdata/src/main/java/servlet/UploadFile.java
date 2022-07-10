package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.GoInMysql;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
        response.setCharacterEncoding("GBK");
             
        DiskFileItemFactory factory = new DiskFileItemFactory();  
        ServletFileUpload upload = new ServletFileUpload(factory);  
        upload.setHeaderEncoding("UTF-8");
        factory.setSizeThreshold(1024 * 1024 * 50);
        File temp_dir = new File("F:\\TEMP");
        factory.setRepository(temp_dir);  
        upload.setSizeMax(1024 * 1024 * 1024 * 20);   //max file byte 20G
        
        
        //File filePath = new File("F:\\TEMP\\"+GetTime.getTime2()+"\\");
        File filePath = new File("F:\\TEMP\\");
        if(!filePath.exists()) filePath.mkdirs();
        try {
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
            	
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        File storeFile = new File(filePath+"\\"+fileName);
                        item.write(storeFile);
                        System.out.println("上传"+storeFile);         
                    }
                }
            }
            PrintWriter out = response.getWriter();
            out.print("<script>alert('上传成功');location.href='pages/tables.jsp';</script>");
            
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        GoInMysql gm = new GoInMysql();
        gm.loadFile(filePath);
        
	}

}
