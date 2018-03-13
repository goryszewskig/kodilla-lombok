package com.crud.tasks.service;

import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTest {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloListDtoStub = new ArrayList<>();
        trelloListDtoStub.add(new TrelloListDto("1", "test list name", false));

        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        trelloBoardDtoStub.add(new TrelloBoardDto("1", "test board name", trelloListDtoStub));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoardDto.size());
        assertEquals("test board name", fetchedTrelloBoardDto.get(0).getName());
    }

    @Test
    public void testFetchEmptyTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoStub = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoStub);

        //When
        List<TrelloBoardDto> fetchedEmptyTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(0, fetchedEmptyTrelloBoardDto.size());
    }
}