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
 * A servlet {@Link ContatoServlet} receberá requisições HTTP relativo a operações de request e response.
 * 
 * @author Daniel Luiz Coelho dos Santos.
 * @since 26/10-2024.
 */
@WebServlet("/ContatoServlet")
public class ContatoServlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	// Declaração de atributos da classe.
	private Contato contato;
	private ContatoService service;
	
	/*
	 * Contrutor da servlet. Será onde iremos instanciar outras classes. Isso
	 * ocorrerá quando a classe ContatoDAO for instanciado.
	 */
	public ContatoServlet() {
		this.contato = new Contato();
		this.service = new ContatoService();
	}

	/**
	 * O método doGet em uma servlet Java é responsável por processar solicitações HTTP GET. 
	 * Ele permite que uma aplicação web receba e responda a dados enviados pelo navegador 
	 * do usuário através do protocolo HTTP GET, geralmente para recuperar informações de 
	 * um servidor. Dentro deste método, você pode ler parâmetros enviados na URL, gerar 
	 * respostas HTML, e muito mais. É essencial para a criação de páginas web dinâmicas e 
	 * interativas.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		// Recursos de navegação entre as páginas da aplicação.
		if (action.equals("/viewagenda")) {
			redirectViewAgenda(request, response);
		} else if (action.equals("/viewnovo")) {
			redirectViewNovoContato(request, response);
		} else if (action.equals("/inserir")) {
			inserir(request, response);
		} else if (action.equals("/vieweditar")) {
			redirectViewEditarCadastro(request, response);
		} else if (action.equals("/atualizar")) {
			atualizar(request, response);
		} else if (action.equals("/excluir")) {
			remover(request, response);
		} else if (action.equals("/impressao")) {
			imprimir(request, response);
		} else {
			response.sendRedirect("index.html");
		}
	}
	
	/**
	 * Método que ao ser solicitado um novo cadastro, irá redirecionar a página .jsp responsável.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectViewNovoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("pages/cadastro/novo.jsp");
		
		rd.forward(request, response);
	}
	
	/**
	 * Método que após o evento para salvar os dados do formulário (novo cadastro), irá salvar no Banco de dado, 
	 * e em seguida será redirecionado para tela de relação dos contatos.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setCelular(request.getParameter("celular"));
		
		this.service.inserirContato(contato);
		
		response.sendRedirect("viewagenda");
	}

	/**
	 * Método que devolverá uma lista de contatos, e em seguida será redirecionado para tela de relação dos contatos.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectViewAgenda(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Contato> lista = this.service.listarContatos();
		request.setAttribute("contatos", lista);
		
		RequestDispatcher rd = request.getRequestDispatcher("pages/cadastro/agenda.jsp");
		
		rd.forward(request, response);
	}
	
	/**
	 * Método que devolverá um único contato, e em seguida será redirecionado para tela de edição.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void redirectViewEditarCadastro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
	 * Método que salvará as atualizações do contato, e em seguida será redirecionado para tela de relação dos contatos.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void atualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		contato.setId(Long.valueOf(request.getParameter("id")));
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setCelular(request.getParameter("celular"));
		
		this.service.alterarContato(contato);
		
		response.sendRedirect("viewagenda");
	}

	/**
	 * Método que excluirá um contato, e em seguida será redirecionado para tela de relação dos contatos.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void remover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		contato.setId(Long.valueOf(idString));
		
		this.service.deletarContato(contato);
		
		response.sendRedirect("viewagenda");
	}

	/**
	 * Método que irá gerar um arquivo em PDF contendo os contatos do Banco de Dados.
	 *
	 * @author Daniel Luiz Coelho dos Santos.
	 * 
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void imprimir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
