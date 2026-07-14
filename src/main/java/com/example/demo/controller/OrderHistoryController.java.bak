package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.OrderDetail;

@Controller
public class OrderHistoryController {

    @GetMapping("/order-history")
    public String showOrderHistory(Model model) {

        // 画面確認用の仮の商品データ
        List<OrderDetail> details1 = List.of(
                new OrderDetail("国産りんご", 250, 2),
                new OrderDetail("緑茶", 480, 1)
        );

        // 画面確認用の仮の注文データ
        Order order1 = new Order(
                1001,
                LocalDateTime.of(2026, 7, 13, 10, 30),
                "山田 太郎",
                "810-0001",
                "福岡県福岡市中央区天神1-1-1",
                "090-1234-5678",
                "test@example.com",
                "クレジットカード",
                details1
        );

        List<OrderDetail> details2 = List.of(
                new OrderDetail("やわらか煎餅", 320, 3)
        );

        Order order2 = new Order(
                1000,
                LocalDateTime.of(2026, 7, 10, 15, 0),
                "山田 太郎",
                "810-0001",
                "福岡県福岡市中央区天神1-1-1",
                "090-1234-5678",
                "test@example.com",
                "代金引換",
                details2
        );

        model.addAttribute("orderList", List.of(order1, order2));

        return "orderHistory";
    }
}