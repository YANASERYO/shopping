package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.OrderInfo;

import shopMail.ShopMail;

@Service
public class MailService {

    private static final int GROUP_NUMBER = 1;
    private static final int TEXT_MAIL = 0;

    public boolean sendOrderCompleteMail(OrderInfo orderInfo) {

        String subject = "【題名】テストメール";

        String body = """
                ここに文章
                お名前: %s
                """.formatted(
                        orderInfo.getShippingName(),
                        orderInfo.getShippingPostalCode(),
                        orderInfo.getShippingAddress(),
                        orderInfo.getShippingPhone(),
                        orderInfo.getShippingPayment()
                );

        try {
            ShopMail.send(
                    GROUP_NUMBER,
                    orderInfo.getShippingEmail(),
                    "KINARIオンラインショップ",
                    subject,
                    body,
                    TEXT_MAIL
            );

            return true;

        } catch (Exception e) {
            System.err.println("注文完了メールの送信に失敗しました。");
            e.printStackTrace();
            return false;
        }
    }
}