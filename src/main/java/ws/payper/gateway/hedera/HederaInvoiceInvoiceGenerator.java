package ws.payper.gateway.hedera;

import org.springframework.stereotype.Component;
import ws.payper.gateway.Invoice;
import ws.payper.gateway.InvoiceGenerator;
import ws.payper.gateway.InvoiceRequest;
import ws.payper.gateway.config.PaymentOptionType;
import ws.payper.gateway.util.QrCodeGenerator;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class HederaInvoiceInvoiceGenerator implements InvoiceGenerator {

    @Resource
    private QrCodeGenerator qrCodeGenerator;

    @Override
    public Invoice newInvoice(InvoiceRequest invoiceRequest) {
        PaymentOptionType paymentOptionType = invoiceRequest.getPaymentOptionType();
        String amount = invoiceRequest.getAmount();
        String account = invoiceRequest.getAccount();

        Map<String, String> params = new HashMap<>();
        params.put("account", account);
        String invoiceId = generateInvoiceId();

        // TODO Workaround until Hedera Wallet can scan memos from QR codes
        amount = amendAmount(amount, invoiceId);

        String contentDescription = MessageFormat.format("{0} [ {1} ]", invoiceRequest.getTitle(), invoiceRequest.getUrl());
        params.put("content_title", contentDescription);
        params.put("url", invoiceRequest.getUrl().toString());
        params.put("amount", amount);
        String hederaAppLink = generateHederaAppLink(account, amount, invoiceId);
        params.put("qr_code", getQrCode(hederaAppLink));
        params.put("pay_req", hederaAppLink);
        params.put("invoice_id", invoiceId);

        return new Invoice(paymentOptionType, invoiceId, params);
    }

    private String generateHederaAppLink(String account, String amount, String memo) {
        String action = "payRequest";

        return MessageFormat.format("https://hedera.app.link/5vuEEQhtLQ?acc={0}&action={1}&a={2}&n={3}", account, action, amount, memo);
    }

    private String amendAmount(String amount, String invoiceId) {
        Long initialAmount = Long.parseLong(amount);

        BigInteger fourDigitIntId = new BigInteger(invoiceId.getBytes()).mod(new BigInteger("10000"));

        Long amendedAmount = initialAmount + fourDigitIntId.longValue();

        return String.valueOf(amendedAmount);
    }

    private String generateInvoiceId() {
        return UUID.randomUUID().toString();
    }

    private String getQrCode(String paymentRequest) {
        return qrCodeGenerator.base64encoded(paymentRequest);
    }

    @Override
    public PaymentOptionType getPaymentOptionType() {
        return PaymentOptionType.HEDERA_HBAR_INVOICE;
    }
}