package com.southsystem.voting.service.impl;

import com.southsystem.voting.domain.Topic;
import com.southsystem.voting.domain.Vote;
import com.southsystem.voting.enums.VoteValue;
import com.southsystem.voting.exception.VotingException;
import com.southsystem.voting.repository.TopicRepository;
import com.southsystem.voting.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository repository;

    /**
     * Métado que busca todas as pautas.
     * @return lista pautas
     */
    @Override
    public List<Topic> getAll() {
        return repository.findAll();
    }

    /**
     * Método para buscar uma pauta por id
     * @param id
     * @return pauta encontrada
     */
    @Override
    public Topic getById(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new VotingException("Not found Topic"));
    }

    /**
     * Método para salvar pauta
     * @param topic
     * @return pauta salva
     */
    @Override
    public Topic save(Topic topic) {
        return repository.save(topic);
    }

    /**
     * Método para deletar pauta
     * @param topic
     */
    @Override
    public void delete(Topic topic) {
        repository.delete(topic);
    }

    /**
     * Método para computar os votos na pauta após fechamento da sessão
     * @param votes
     * @param topic
     */
    @Override
    public void addVotes(Set<Vote> votes, Topic topic){
        topic.addVotesTotal(votes.size());
        topic.addVoteYes((int) votes.stream().filter(VoteValue.YES::equals).count());
        topic.addVoteNo((int) votes.stream().filter(VoteValue.NO::equals).count());
        save(topic);
    }
}
