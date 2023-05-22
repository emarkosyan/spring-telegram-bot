package com.github.emarkosyan.stb.service;


import com.github.emarkosyan.stb.repository.TelegramuserRepository;
import com.github.emarkosyan.stb.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserServiceImpl implements TelegramUserService{

    private final TelegramuserRepository telegramuserRepository;

    @Autowired
    public TelegramUserServiceImpl(TelegramuserRepository telegramuserRepository) {
        this.telegramuserRepository = telegramuserRepository;
    }

    @Override
    public void save(TelegramUser telegramUser) {
        telegramuserRepository.save(telegramUser);
    }

    @Override
    public List<TelegramUser> retrieveAllActiveUsers() {
        return telegramuserRepository.findAllByActiveTrue();
    }

    @Override
    public Optional<TelegramUser> findByChatId(String chatId) {
        return telegramuserRepository.findById(chatId);
    }
}
