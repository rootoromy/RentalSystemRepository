package com.ikeda.presentation.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ikeda.presentation.form.OrderListForm; // 【重要】OrderListFormをインポートimport com.ikeda.service.OrderItemService;
import com.ikeda.service.OrderItemService;

@Controller
public class AdminOrderController {

    private final OrderItemService orderItemService;

    public AdminOrderController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    // 注文一覧表示
    @GetMapping("/admin/orders")
    public String list(Model model) {
    	List<OrderListForm> orders = orderItemService.findAll();        model.addAttribute("orders", orders);
        return "admin/order-items";
    }
}
