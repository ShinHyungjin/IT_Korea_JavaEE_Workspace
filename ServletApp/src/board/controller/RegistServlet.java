package board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import board.model.Board;
import board.model.BoardDAO;
import common.file.FileManager;

public class RegistServlet extends HttpServlet {
	BoardDAO boardDAO = new BoardDAO();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();

		ServletContext context = request.getServletContext();
		String saveDir = context.getRealPath("/data");

		factory.setSizeThreshold(2 * 1024 * 1024);
		factory.setDefaultCharset("utf-8");
		factory.setRepository(new File(saveDir));

		request.setCharacterEncoding("utf-8");
		ServletFileUpload upload = new ServletFileUpload(factory);
		Board board = new Board();
		boolean flag = false;

		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {
				if (item.isFormField()) {
					if (item.getFieldName().equals("title")) {
						board.setTitle(item.getString());
					} else if (item.getFieldName().equals("writer")) {
						board.setWriter(item.getString());
					} else if (item.getFieldName().equals("content")) {
						board.setContent(item.getString());
					}
				} else {
					long time = System.currentTimeMillis();
					String newName = time + "." + FileManager.getExtend(item.getName());
					board.setFilename(newName);

					item.write(new File(saveDir + "/" + newName));
					flag = true;
				}
			}
			
			if(flag) {
				int result = boardDAO.insert(board);
				if(result == 0 ) {
					out.print("<script>");
					out.print("alert('등록실패')");
					out.print("history.back();");
					out.print("</script>");
				}else {
					out.print("<script>");
					out.print("alert('등록성공')");
					out.print("location.href='/board/list.jsp';");
					out.print("</script>");
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
