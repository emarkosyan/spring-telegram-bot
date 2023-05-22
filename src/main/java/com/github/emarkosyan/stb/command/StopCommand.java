package com.github.emarkosyan.stb.command;

import com.github.emarkosyan.stb.service.SendBotMessageService;
import com.github.emarkosyan.stb.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StopCommand implements Command{

    private final SendBotMessageService sendBotMessageService;

    public static final String STOP_MESSAGE = "Деактивировался \uD83D\uDE1F.";
    private final TelegramUserService telegramUserService;
    public StopCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), STOP_MESSAGE);
        telegramUserService.findByChatId(update.getMessage().getChatId().toString()).ifPresent(
                it -> {
                    it.setActive(false);
                    telegramUserService.save(it);
                });
    }
}
