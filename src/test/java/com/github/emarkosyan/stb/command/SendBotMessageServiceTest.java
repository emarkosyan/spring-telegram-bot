package com.github.emarkosyan.stb.command;

import com.github.emarkosyan.stb.bot.SpringTelegramBot;
import com.github.emarkosyan.stb.service.SendBotMessageService;
import com.github.emarkosyan.stb.service.SendBotMessageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@DisplayName("Unit-level testing for SendBotMessageServiceTest")
public class SendBotMessageServiceTest {

    private SendBotMessageService sendBotMessageService;
    private SpringTelegramBot springTelegramBot;

    @BeforeEach
    public void init(){
        springTelegramBot = Mockito.mock(SpringTelegramBot.class);
        sendBotMessageService = new SendBotMessageServiceImpl(springTelegramBot);
    }

    @Test
    public void shouldProperlySendMessage() throws TelegramApiException{

        String chatId = "test_chat_id";
        String message = "test_message";

        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(message);
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);

        sendBotMessageService.sendMessage(chatId, message);

        Mockito.verify(springTelegramBot).execute(sendMessage);
    }
}
