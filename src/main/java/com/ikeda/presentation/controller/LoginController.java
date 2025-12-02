package com.ikeda.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ikeda.presentation.form.LoginForm;
import com.ikeda.service.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;  // インスタンスを注入

    // GET /login → ログイン画面表示
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login"; // templates/login.html
    }

	@PostMapping("/login")
	public String doLogin(
			@RequestParam String email,
			@RequestParam String password,
			Model model,HttpSession session) {

		boolean result = loginService.loginCheck(email, password);

		
		if (result) {
			session.setAttribute("loginUser", email);
			return "redirect:/index"; // ログイン成功時：トップページに遷移する
		} else {
			model.addAttribute("error", "メールアドレスまたはパスワードが違います");
			return "login";//ログイン失敗時：ログインページに遷移する
		}
		

	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // セッションを完全に破棄
	    return "redirect:/";  // トップページへ戻る
	}

}
