package br.com.projeto.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.projeto.controller.service.ContatoService;
import br.com.projeto.model.Contato;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContatoServlet
 */
@WebServlet("/ContatoServlet")
public class ContatoServlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** Atributos da classe. 
	 **/
	private Contato contato;
	private ContatoService service;
	
	/**
	 * Instantiates a new controller.
	 */
	public ContatoServlet() {
		this.contato = new Contato();
		this.service = new ContatoService();
	}

	/**
	 * Método doGet.
	 * Receberá conexão HTTP.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		if (action.equals("/main")) {
			contatos(request, response);
		} else if (action.equals("/novo")) {
			redirectNovoContato(request, response);
		} else if (action.equals("/insert")) {
			adicionarContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução adicionará um contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectNovoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("pages/cadastro/novo.jsp");
		
		rd.forward(request, response);
	}
	
	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução adicionará um contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setCelular(request.getParameter("celular"));
		
		this.service.inserirContato(contato);
		
		response.sendRedirect("main");
	}

	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução listará contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Contato> lista = this.service.listarContatos();
		request.setAttribute("contatos", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("pages/cadastro/agenda.jsp");
		
		rd.forward(request, response);
	}
	
	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução listará um único contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		contato.setId(Long.valueOf(request.getParameter("id")));
		this.service.selecionarContato(contato);
		
		request.setAttribute("id", contato.getId());
		request.setAttribute("nome", contato.getNome());
		request.setAttribute("telefone", contato.getTelefone());
		request.setAttribute("celular", contato.getCelular());
		
		RequestDispatcher rd = request.getRequestDispatcher("pages/cadastro/editar.jsp");
		
		rd.forward(request, response);
	}

	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução irá atualizar um contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		contato.setId(Long.valueOf(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setCelular(request.getParameter("celular"));
		
		this.service.alterarContato(contato);
		
		response.sendRedirect("main");
	}

	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução irá ecluir um contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		contato.setId(Long.valueOf(idString));
		
		this.service.deletarContato(contato);
		
		response.sendRedirect("main");
	}

	/**
	 * Método que receberá uma solicitação HTTP, onde ao final da execução irá gerar um PDF contendo os contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		try {
			response.setContentType("apllication/pdf");
			response.addHeader("Content-Disposition", "inline; filename=" + "contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de contatos:"));
			documento.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Telefone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Celular"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			ArrayList<Contato> lista = this.service.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelefone());
				tabela.addCell(lista.get(i).getCelular());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
