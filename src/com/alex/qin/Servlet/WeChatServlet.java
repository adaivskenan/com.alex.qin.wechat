package com.alex.qin.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alex.qin.Service.VldtSgntrService;

/**
 * Servlet implementation class weChatServlet
 */
@WebServlet(description = "΢����֤�����ַ��Ч��", urlPatterns = { "/weChatServlet" })
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeChatServlet() {
        super();
    }
	
	  /**
	   * <h6>��֤��������ַ����Ч�ԣ�</h6>
	   * <p>
	   * �������ύ��Ϣ��΢�ŷ�����������GET������д�ķ�������ַURL�ϣ�GET����Я���ĸ�������</br><ul>
	   * 		<li>signature	΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp��nonce������</li>
	   * 		<li>timestamp	ʱ���</li>
	   * 		<li>nonce	�����</li>
	   * 		<li>echostr	����ַ���</li>
	   * </p>
	   * ������ͨ������signature���������У��({@link com.alex.qin.Service.VldtSgntrService} ����ȷ�ϴ˴�GET��������΢�ŷ���������ԭ������echostr�������ݣ��������Ч����Ϊ�����߳ɹ����������ʧ�ܡ�</br>
	   * 
	   */
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//����
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//У��
		VldtSgntrService vss = new VldtSgntrService();
		if(vss.ValidateSignature(signature, timestamp, nonce)){
			PrintWriter out = response.getWriter();
			out.print(echostr);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
