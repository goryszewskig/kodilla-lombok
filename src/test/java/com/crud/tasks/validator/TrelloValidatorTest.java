package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.* ;

public class TrelloValidatorTest {
    private TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    public void testValidateTrelloBoards(){
        //Given
        TrelloBoard firstBoardStub = new TrelloBoard("0", "test", new ArrayList<>());
        TrelloBoard secondBoardStub = new TrelloBoard("1", "board name", new ArrayList<>());
        List<TrelloBoard> trelloBoardStub = new ArrayList<>();
        trelloBoardStub.add(firstBoardStub);
        trelloBoardStub.add(secondBoardStub);

        //When
        List<TrelloBoard> validatedTrelloBoards = trelloValidator.validateTrelloBoards(trelloBoardStub);
        List<TrelloBoard> validatedEmptyTreooloBoards = trelloValidator.validateTrelloBoards(new ArrayList<>());

        //Then
        assertEquals(1, validatedTrelloBoards.size());
        assertEquals("board name", validatedTrelloBoards.get(0).getName());
        assertEquals(0,validatedEmptyTreooloBoards.size());
    }
}
