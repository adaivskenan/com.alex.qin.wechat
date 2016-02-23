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
@WebServlet(description = "微信验证服务地址有效性", urlPatterns = { "/weChatServlet" })
public class WeChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WeChatServlet() {
        super();
    }
	
	  /**
	   * <h6>验证服务器地址的有效性：</h6>
	   * <p>
	   * 开发者提交信息后，微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带四个参数：</br><ul>
	   * 		<li>signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp、nonce参数。</li>
	   * 		<li>timestamp	时间戳</li>
	   * 		<li>nonce	随机数</li>
	   * 		<li>echostr	随机字符串</li>
	   * </p>
	   * 开发者通过检验signature对请求进行校验({@link com.alex.qin.Service.VldtSgntrService} 。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。</br>
	   * 
	   */
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//参数
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//校验
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
