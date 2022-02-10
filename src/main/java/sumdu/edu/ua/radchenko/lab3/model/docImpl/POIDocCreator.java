package sumdu.edu.ua.radchenko.lab3.model.docImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sumdu.edu.ua.radchenko.lab3.model.DocCreator;
import sumdu.edu.ua.radchenko.lab3.model.Movie;

@Component
@PropertySource("classpath:application.properties")
public class POIDocCreator implements DocCreator {

    private String path;

    private final static Logger logger = Logger.getLogger(DocCreatorE.class);

    @Autowired
    public void setPath(@Value("${directory.path}")  String path) {
        this.path = path;
    }

    @Override
    public void generateDoc(Movie movie){

        logger.info("calling generateDocPOI:" + movie.getMovieName());

        try {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

            //Write the Document in file system
            FileOutputStream out = new FileOutputStream(new File(path + movie.getMovieName() + "POI" + ".docx"));

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Title: " + movie.getMovieName());
            run.addBreak();
            run.setText("Released: " + movie.getRelease().toString());
            run.addBreak();
            run.setText("Runtime: " + movie.getRuntime());
            run.addBreak();
            run.setText("Genre: " + movie.getGenre());
            run.addBreak();
            run.setText("Director: " + movie.getDirectors());
            run.addBreak();
            run.setText("Writer: " + movie.getWriters());
            run.addBreak();
            run.setText("Actors: " + movie.getActors());
            run.addBreak();
            run.setText("Plot: " + movie.getPlot());
            run.addBreak();
            run.setText("Rating: " + movie.getRatings().get(0));
            run.addBreak();
            run.setText("Rating: " + movie.getRatings().get(1));
            run.addBreak();
            run.setText("Rating: " + movie.getRatings().get(2));

            document.write(out);
            out.close();
        } catch (IOException e){
            logger.error("Error in generateDocPOI: " + e.getMessage());
        }
        logger.info("exit from generateDocPOI");
    }
}
