package sumdu.edu.ua.radchenko.lab3.model;

import com.spire.doc.*;
import com.spire.doc.documents.*;
import com.spire.doc.fields.TextRange;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sumdu.edu.ua.radchenko.lab3.connection.ConnectionImpl;

import java.awt.*;

@Component
@PropertySource("classpath:application.properties")
public class DocCreator {

    private String path;

    private final static Logger logger = Logger.getLogger(DocCreator.class);

    @Autowired
    public void setPath(@Value("${directory.path}")  String path) {
        this.path = path;
    }

    public void generateDoc(Movie movie){

        logger.info("calling generateDoc:" + movie.getMovieName());
        //Create a Document instance
        Document document = new Document();

        //Add a section
        Section section = document.addSection();

        //Define data to create table
        String[] header = {"Params", "Film"};
        String[][] data =
                {
                        new String[]{"Title", movie.getMovieName()},
                        new String[]{"Released", movie.getRelease().toString()},
                        new String[]{"Runtime", movie.getRuntime()},
                        new String[]{"Genre", movie.getGenre()},
                        new String[]{"Director", movie.getDirectors()},
                        new String[]{"Writer", movie.getWriters()},
                        new String[]{"Actors", movie.getActors()},
                        new String[]{"Plot", movie.getPlot()},
                        new String[]{"Rating", movie.getRatings().get(0)},
                        new String[]{"Rating", movie.getRatings().get(1)},
                        new String[]{"Rating", movie.getRatings().get(2)},
                };

        //Add a table
        Table table = section.addTable();
        table.resetCells(data.length + 1, header.length);
        table.applyStyle(DefaultTableStyle.Colorful_List);
        table.getTableFormat().getBorders().setBorderType(BorderStyle.Single);

        //Set the first row as table header and add data
        TableRow row = table.getRows().get(0);
        row.isHeader(true);
        row.setHeight(20);
        row.setHeightType(TableRowHeightType.Exactly);
        row.getRowFormat().setBackColor(Color.gray);
        for (int i = 0; i < header.length; i++) {
            row.getCells().get(i).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
            Paragraph p = row.getCells().get(i).addParagraph();
            p.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);
            TextRange range1 = p.appendText(header[i]);
            range1.getCharacterFormat().setFontName("Arial");
            range1.getCharacterFormat().setFontSize(12f);
            range1.getCharacterFormat().setBold(true);
        }

        //Add data to the rest of rows
        for (int r = 0; r < data.length; r++) {
            TableRow dataRow = table.getRows().get(r + 1);
            dataRow.setHeight(25);
            dataRow.setHeightType(TableRowHeightType.Exactly);
            dataRow.getRowFormat().setBackColor(Color.white);
            for (int c = 0; c < data[r].length; c++) {
                dataRow.getCells().get(c).getCellFormat().setVerticalAlignment(VerticalAlignment.Middle);
                TextRange range2 = dataRow.getCells().get(c).addParagraph().appendText(data[r][c]);
                range2.getCharacterFormat().setFontName("Arial");
                range2.getCharacterFormat().setFontSize(10f);
            }
        }

        //Set background color for cells
        for (int j = 1; j < table.getRows().getCount(); j++) {
            if (j % 2 == 0) {
                TableRow row2 = table.getRows().get(j);
                for (int f = 0; f < row2.getCells().getCount(); f++) {
                    row2.getCells().get(f).getCellFormat().setBackColor(new Color(173, 216, 230));
                }
            }
        }

        //Save the document
        document.saveToFile(path + movie.getMovieName() + ".docx", FileFormat.Docx_2013);

        logger.info("exit from generateDoc");
    }
}
