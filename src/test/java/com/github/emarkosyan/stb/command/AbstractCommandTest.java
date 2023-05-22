package com.github.emarkosyan.stb.command;

import com.github.emarkosyan.stb.bot.SpringTelegramBot;
import com.github.emarkosyan.stb.service.SendBotMessageService;
import com.github.emarkosyan.stb.service.SendBotMessageServiceImpl;
import com.github.emarkosyan.stb.service.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;

abstract class AbstractCommandTest {

    protected SpringTelegramBot springTelegramBot = Mockito.mock(SpringTelegramBot.class);
    protected TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(springTelegramBot);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    @Test
    public void shouldProperlyExecuteCommand() throws TelegramApiException{

        Long chatId = 1234567824356L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);

        getCommand().execute(update);

        Mockito.verify(springTelegramBot).execute(sendMessage);
    }
}
