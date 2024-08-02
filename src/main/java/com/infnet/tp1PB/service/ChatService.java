package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Chat;
import com.infnet.tp1PB.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat criarChat(Chat chat) {
        chat.setTimestamp(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    public List<Chat> listarChats() {
        return chatRepository.findAll();
    }

    public Optional<Chat> encontrarPorId(Long id) {
        return chatRepository.findById(id);
    }

    public void deletarChat(Long id) {
        chatRepository.deleteById(id);
    }

    public Chat atualizarChat(Long id, Chat chatAtualizado) {
        Optional<Chat> chatOptional = chatRepository.findById(id);
        if (chatOptional.isPresent()) {
            Chat chatExistente = chatOptional.get();
            chatExistente.setRemetente(chatAtualizado.getRemetente());
            chatExistente.setDestinatario(chatAtualizado.getDestinatario());
            chatExistente.setMensagem(chatAtualizado.getMensagem());
            // Atualize outros atributos conforme necessário
            return chatRepository.save(chatExistente);
        } else {
            throw new IllegalArgumentException("Chat não encontrado para o ID: " + id);
        }
    }
}
