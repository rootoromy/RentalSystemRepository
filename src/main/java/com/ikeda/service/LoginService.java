package com.ikeda.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

    // 仮のユーザー情報（ダミー用）
    private final String DUMMY_EMAIL = "test@example.com";
    private final String DUMMY_PASSWORD = "password";

    // ログインチェック用メソッド
    public boolean loginCheck(String email, String password) {
        // 仮に1件のユーザー情報と照合するだけ
        return DUMMY_EMAIL.equals(email) && DUMMY_PASSWORD.equals(password);
    }
}


/*DBから値を持ってくるように記述
package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ikeda.entity.Member;
import com.ikeda.repository.MemberRepository;

@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * ログインチェック
     * @param email 入力されたメールアドレス
     * @param password 入力されたパスワード
     * @return 認証成功なら true、失敗なら false
     *//*
    public boolean loginCheck(String email, String password) {
        // DB からメールアドレスでユーザーを検索
        Member member = memberRepository.findByEmail(email);
        
        if (member == null) {
            // ユーザーが存在しない
            return false;
        }

        // パスワード照合（今は平文のまま、将来的にはハッシュ化?）
        return member.getPassword().equals(password);
        // 修正例
        return new BCryptPasswordEncoder().matches(password, member.getPassword());

    }
}
*/