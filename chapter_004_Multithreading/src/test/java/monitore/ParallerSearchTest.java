package monitore;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Ivan Sliusar on 31.10.2017.
 * Red Line Soft corp.
 */
public class ParallerSearchTest {
    @Test
    public void searchFiles() {
        List<String> filterlist = new ArrayList<>();
        filterlist.add(".txt");
        filterlist.add(".doc");

        ParallerSearch parallerSearch = new ParallerSearch();
        parallerSearch.searchFiles("C:\\Test\\", "I like it", filterlist);
    }

}