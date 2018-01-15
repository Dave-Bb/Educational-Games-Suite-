package tgs;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CreatePDF {

    //creates detailed PDF for one learner and one game
    public static void createPDFSingleDetailed(ResultSet rs, String nameID, String gameID) {

        //variables to be used for PDF generation
        String name = "";
        String game = "";
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        try {
            //specify name and location of the file
            String fileName = "C:/PDFs/Results - " + nameID + " - " + gameID + " - " + timeStamp + ".pdf";

            //create a document
            PDDocument doc = new PDDocument();
            //create document page
            PDPage page = new PDPage();

            //add page to document
            doc.addPage(page);

            //add content Stream which is used to add all content onto the PDF
            PDPageContentStream content = new PDPageContentStream(doc, page);

            //ArrayLists to store the results and dates
            ArrayList<String> s = new ArrayList();
            ArrayList<String> dates = new ArrayList();

            //getting the name, game name, results and result dates from the ResultSet
            while (rs.next()) {
                name = rs.getString("LearnerName");
                game = rs.getString("GameName");
                dates.add(rs.getString("ResultDate"));
                s.add(rs.getString("Result"));
            }

            //Create heading
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 26);
            content.moveTextPositionByAmount(140, 750);
            content.drawString("Learner Report - " + timeStamp);
            content.endText();

            //insert name field
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.moveTextPositionByAmount(80, 700);
            content.drawString("Name : ");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(180, 700);
            content.drawString(name);
            content.endText();

            //insert game name field
            content.beginText();
            content.moveTextPositionByAmount(80, 650);
            content.drawString("Game Name : ");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(180, 650);
            content.drawString(game);
            content.endText();

            //insert results field
            content.beginText();
            content.moveTextPositionByAmount(80, 600);
            content.drawString("Results : ");
            content.endText();

            //creating diagram lines and descriptions
            content.drawLine(120, 100, 120, 320);
            content.beginText();
            content.moveTextPositionByAmount(100, 330);
            content.drawString("Result");
            content.endText();
            content.drawLine(120, 100, 480, 100);

            int x = 160;
            int y = 600;
            int rectX = 130;
            int rectY = 100;
            int rectHeight;
            int rectWidth = 25;

            //insert results as well as the bars for the graphs
            for (int j = 0; j < s.size(); j++) {
                content.beginText();
                content.moveTextPositionByAmount(x, y);
                content.drawString("#" + (j + 1) + ": " + s.get(j) + "/10" + " - " + dates.get(j));
                rectHeight = Integer.parseInt(s.get(j)) * 20;
                content.endText();
                content.fillRect(rectX, rectY, rectWidth, rectHeight);
                y = y - 25;
                rectX += 35;
            }

            content.close(); //close content stream
            doc.save(fileName); //save the document in the previously specified location
            doc.close();

            JOptionPane.showMessageDialog(null, "File created!");

        } catch (IOException | SQLException ex) {
            Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return doc;
    }

    public static void createPDFSingleSummary(ResultSet rs, String nameID) {

        String game;
        String average;

        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        try {

            System.out.println("Create Simple PDF file with Text");
            String fileName = "C:/PDFs/Summary - " + nameID + " - " + timeStamp + ".pdf"; // name of our file

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 26);
            content.moveTextPositionByAmount(130, 750);
            content.drawString("Learner Report Summary - " + timeStamp);
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.moveTextPositionByAmount(80, 700);
            content.drawString("Name : ");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(220, 700);
            content.drawString(nameID);
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(80, 650);
            content.drawString("Game Averages : ");
            content.endText();

            int x = 220;
            int y = 650;

            while (rs.next()) {
                game = rs.getString("GameName");
                average = rs.getString("Average");
                content.beginText();
                content.moveTextPositionByAmount(x, y);
                content.drawString(game + ": " + average + "/10");
                content.endText();
                y = y - 25;
            }

            content.close();
            doc.save(fileName);
            doc.close();

            System.out.println("your file created in : " + System.getProperty("user.dir"));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return doc;
    }

    public static void createPDFClassSummary(ResultSet rs, String grade) throws IOException {

        ArrayList<Learner> learner = new ArrayList<>();
        //Learner[] learner = new Learner[50];
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy").format(new Date());

        try {
            System.out.println("Create Simple PDF file with Text");
            String fileName = "C:/PDFs/Summary - Class " + grade + " - " + timeStamp + ".pdf"; // name of our file

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 26);
            content.moveTextPositionByAmount(130, 750);
            content.drawString("Class Summary - " + timeStamp);
            content.endText();

            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.moveTextPositionByAmount(80, 700);
            content.drawString("Class : ");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(150, 700);
            content.drawString(grade);
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(80, 650);
            content.drawString("Learner: ");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(140, 650);
            content.drawString("Falling Letters");
            content.endText();

            content.beginText();
            content.moveTextPositionByAmount(240, 650);
            content.drawString("Hangman");
            content.endText();

            int x = 80;
            int y = 625;

            while (rs.next()) {
                Learner l = new Learner();
                l.setName(rs.getString("LearnerName"));
                l.setGame(rs.getString("GameName"));
                l.setAverage(rs.getDouble("Average"));
                learner.add(l);
            }

            for (int i = 0; i < learner.size(); i++) {
                if (i > 0) {
                    if (!learner.get(i).getName().equals(learner.get(i - 1).getName())) {
                        y -= 25;
                        x = 80;

                        content.beginText();
                        content.moveTextPositionByAmount(x, y);
                        content.drawString(learner.get(i).getName());
                        content.endText();
                    }

                } else {
                    content.beginText();
                    content.moveTextPositionByAmount(x, y);
                    content.drawString(learner.get(i).getName());
                    content.endText();
                }
                
                switch (learner.get(i).getGame()) {
                    case "Falling Letters":
                        x = 140;
                        break;
                    case "Hangman":
                        x = 240;
                        break;
                }
                content.beginText();
                content.moveTextPositionByAmount(x, y);
                content.drawString(learner.get(i).toString());
                content.endText();
            }

            content.close();
            doc.save(fileName);
            doc.close();

        } catch (SQLException ex) {
            Logger.getLogger(CreatePDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
