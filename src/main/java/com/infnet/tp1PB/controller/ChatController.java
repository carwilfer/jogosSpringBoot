package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Chat;
import com.infnet.tp1PB.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public Chat criarChat(@RequestBody Chat chat) {
        return chatService.criarChat(chat);
    }

    @GetMapping
    public List<Chat> listarChats() {
        return chatService.listarChats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> encontrarPorId(@PathVariable Long id) {
        Optional<Chat> chat = chatService.encontrarPorId(id);
        return chat.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarChat(@PathVariable Long id) {
        chatService.deletarChat(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chat> atualizarChat(@PathVariable Long id, @RequestBody Chat chatAtualizado) {
        Optional<Chat> chat = chatService.encontrarPorId(id);
        if (chat.isPresent()) {
            Chat chatExistente = chat.get();
            chatExistente.setMensagem(chatAtualizado.getMensagem());
            // Atualize outros atributos conforme necessário
            Chat chatAtualizadoSalvo = chatService.criarChat(chatExistente);
            return ResponseEntity.ok(chatAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
