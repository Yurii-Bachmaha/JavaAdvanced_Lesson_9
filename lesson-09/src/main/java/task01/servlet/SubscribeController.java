package task01.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task01.domain.Subscribe;
import task01.service.SubscribeService;
import task01.service.impl.SubscribeServiceImpl;

public class SubscribeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private	SubscribeService subscribeService = SubscribeServiceImpl.getSubscribeService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String magazineId = request.getParameter("magazineId");
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		Subscribe subscribe = new Subscribe(userId, Integer.parseInt(magazineId), true, new Date(), 1);
		subscribeService.create(subscribe);
		
		response.setContentType("text");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("Success");
	}

}