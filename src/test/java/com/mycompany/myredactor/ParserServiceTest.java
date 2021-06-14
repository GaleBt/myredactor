package com.mycompany.myredactor;

import com.mycompany.myredactor.command.Command;
import com.mycompany.myredactor.command.CommandFactory;
import com.mycompany.myredactor.command.CommandType;
import com.mycompany.myredactor.exception.UnknownCommandException;
import com.mycompany.myredactor.service.ParserService;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParserServiceTest {

    private final CommandFactory commandFactory = mock(CommandFactory.class);
    private final ParserService parserService = new ParserService(commandFactory);

    @Before
    public void setUp() {
        when(commandFactory.newDeleteCommand(any(Integer.class))).thenCallRealMethod();
        when(commandFactory.newInsertCommand(any(Integer.class), any(String.class))).thenCallRealMethod();
        when(commandFactory.newOpenFile(any(String.class))).thenCallRealMethod();
        when(commandFactory.newQuitCommand()).thenCallRealMethod();
        when(commandFactory.newListCommand()).thenCallRealMethod();
        when(commandFactory.newSaveCommand()).thenCallRealMethod();
    }

    @Test
    public void testDelete() {
        Command command = parserService.parse("del 4");
        verify(commandFactory).newDeleteCommand(4);
        assertEquals(CommandType.DELETE, command.getType());
    }

    @Test
    public void testInsert() {
        Command command = parserService.parse("ins 4 aaa");
        verify(commandFactory).newInsertCommand(4, "aaa");
        assertEquals(CommandType.INSERT, command.getType());
    }

    @Test
    public void testList() {
        Command command = parserService.parse("list");
        verify(commandFactory).newListCommand();
        assertEquals(CommandType.LIST, command.getType());
    }
    
    @Test
    public void testSave() {
        Command command = parserService.parse("   save   ");
        verify(commandFactory).newSaveCommand();
        assertEquals(CommandType.SAVE, command.getType());
    }

    @Test
    public void testQuit() {
        Command command = parserService.parse(" quit   ");
        verify(commandFactory).newQuitCommand();
        assertEquals(CommandType.QUIT, command.getType());
    }

    @Test(expected = UnknownCommandException.class)
    public void testUnknown() {
        parserService.parse(" unknown   ");
    }
}
