package com.ikeda.presentation.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ikeda.entity.DvdItem; // DvdItem Entity をインポート
import com.ikeda.presentation.form.ProductForm;
import com.ikeda.service.DvdItemService;

@Controller
public class AdminProductController {
	
	@Autowired
	private DvdItemService productService;

    // 商品一覧
    @GetMapping("/admin/products")
    public String showProductList(Model model) {
        model.addAttribute("product", productService.findAll());
        return "admin/product-list";//商品一覧ページへ遷移する
    }

    // 商品新規登録フォーム
    @GetMapping("/admin/products/new")
    public String showProductForm(Model model) {
        model.addAttribute("product", new ProductForm());
        return "admin/product-form";//新規商品登録ページへ遷移する
    }
 // 商品新規登録実行 (POST /admin/product-save)
    @PostMapping("/admin/product-save")
    public String registerProduct(
            @ModelAttribute("product") ProductForm form, 
            RedirectAttributes redirectAttributes) {
        
        // 1. Form -> Entity への変換
        DvdItem dvdItem = new DvdItem();
        
        // データのコピー (IDは新規登録なので不要)
        dvdItem.setTitle(form.getTitle());
        dvdItem.setDescription(form.getDescription());
        dvdItem.setPricePerDay(form.getPricePerDay());
        dvdItem.setStock(form.getStock());
        
        // 画像ファイルの処理は今回はスキップし、仮のファイル名を設定
        // ※ 実際のアプリケーションでは、ここでファイルアップロード処理と MultipartFile が必要
        dvdItem.setImageFileName("placeholder.jpg"); 

        // 2. Serviceへの保存委譲
        productService.save(dvdItem);
        
        // 3. 成功メッセージをリダイレクト先へ渡し、一覧ページへ遷移
        redirectAttributes.addFlashAttribute("success", "商品を新規登録しました。");
        return "redirect:/admin/products"; 
    }
}