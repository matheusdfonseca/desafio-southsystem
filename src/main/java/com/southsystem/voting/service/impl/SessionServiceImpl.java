package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Session;
import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.dto.request.SessionRequest;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.SessionRepository;
import com.southsystem.voting.service.SessionService;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class SessionServiceImpl implements SessionService {
    @Autowired
    SessionRepository repository;
    @Autowired
    TopicService topicService;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    /**
     * Método responsável por trazer todas as sessões.
     * @return lista de sessões.
     */
    @Override
    public List<Session> getAll() {
        return repository.findAll();
    }

    /**
     * Método por trazer todas as sessões que estão encerradas.
     * @return lista de sessões
     */
    @Override
    public List<Session> getAllSessionNotClosed() {
        return repository.findAllByIsClosedIsFalseAndTimeEndIsAfter(LocalDateTime.now());
    }

    /**
     *  Método responsável por trazer todas as sessões que não foram encerradas devidamente.
     *  Mótivos : Aplicação caiu no meio do processo.
     * @return lista de sessões
     */
    @Override
    public List<Session> getAllIncorretSessionNotClosed() {
        return repository.findAllByIsClosedIsFalseAndTimeEndIsBefore(LocalDateTime.now());
    }

    /**
     *  Método responsável por abrir uma sessão para votação.
     * @param request
     * @return sessão que foi aberta
     */
    @Override
    public Session open(SessionRequest request) {
        Topic topic = topicService.getById(request.getTopic());
        Long duration = request.getDuration();
        Session session = new Session(duration, topic);
        session.setTimeStart(LocalDateTime.now());
        session.updateTimeEnd();

        return save(session);
    }

    /**
     * Método para buscar sessão por id.
     * @param id
     * @return sessão encontrada
     */
    @Override
    public Session getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new VotingException("Not found session"));
    }

    /**
     * Método para salvar as sessões.
     * @param session
     * @return sessão salva.
     */
    @Override
    public Session save(Session session) {
        return repository.save(session);
    }

    /**
     * Método para deletar as sessões.
     * @param session
     */
    @Override
    public void delete(Session session) {
        repository.delete(session);
    }

    /***
     * Metódo para fechar as sessões que estão abertas.
     * @param session
     */
    @Override
    public void closeSession(Session session) {
        executorService.schedule(() -> {
            session.setIsClosed(Boolean.TRUE);
            save(session);
            topicService.addVotes(session.getVotes(), session.getTopic());
        }, session.getDuration(), TimeUnit.MINUTES);

    }



}
