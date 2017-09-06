package br.ufpr.tads.dac.lol.web;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.ufpr.tads.dac.lol.data.HibernateUtil;
import br.ufpr.tads.dac.lol.model.Category;
import br.ufpr.tads.dac.lol.model.Stock;
import br.ufpr.tads.dac.lol.model.StockCategory;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Hello() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		test();
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void test() {
		System.out.println("Hibernate many to many - join table + extra column (Annotation)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7052");
		stock.setStockName("PADINI");

		Category category = new Category("CONSUMER", "CONSUMER COMPANY");
		// new category, need save to get the id first
		session.save(category);

		StockCategory stockCategory = new StockCategory();

		stockCategory.setStock(stock);
		stockCategory.setCategory(category);
		stockCategory.setCreatedDate(new Date());
		stockCategory.setCreatedBy("system");

		stock.getStockCategories().add(stockCategory);

		session.save(stock);
		// session.getTransaction().commit();

		// session.beginTransaction();
		stock.getStockCategories().remove(stockCategory);
		session.save(stock);
		session.getTransaction().commit();

		System.out.println("Done");
	}

}
