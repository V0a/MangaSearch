import java.awt.EventQueue;

import javax.swing.JOptionPane;

public class Query {
    String query="";
    SearchTags searchTags;
    MySQLConnector mySQL;

    public Query() {
        mySQL=new MySQLConnector(
                JOptionPane.showInputDialog("Host:","127.0.0.1"),
                JOptionPane.showInputDialog("Database Name","mangadata"),
                JOptionPane.showInputDialog("Username",""),
                JOptionPane.showInputDialog("Password",""));
        searchTags = new SearchTags();
    }


    public void newQuery(String mangaName, String author,      String artist,    int rating,
                         String language,  String demographic, String pubStatus, String tags) {
        query="";
        query+="SELECT \r\n" +
                "manga.MangaID AS ID,\r\n" +
                "manga.MangaName AS Name,\r\n" +
                "manga.Rating AS Rating,\r\n" +
                "manga.Views AS Views,\r\n" +
                "manga.Follows AS Follows,\r\n" +
                "manga.TotalChapters AS TotalChapters,\r\n" +
                "manga.Link AS Link,\r\n" +
                "manga.ImageLink AS ImageLink,\r\n"+
                "manga.Description AS Description,\r\n" +
                "h.LanguageName AS Language,\r\n" +
                "i.DemographicName AS Demographic,\r\n" +
                "j.PublicationStatusName AS PublicationStatus,\r\n" +
                "group_concat(DISTINCT g.AlternativeName SEPARATOR ',  ') AS AlternativeNames,\r\n" +
                "group_concat(DISTINCT a.GenreName SEPARATOR ', ') AS Genres, \r\n" +
                "group_concat(DISTINCT b.ThemeName SEPARATOR ', ') AS Themes, \r\n" +
                "group_concat(DISTINCT c.FormatName SEPARATOR ', ') AS Format, \r\n" +
                "group_concat(DISTINCT d.ContentName SEPARATOR ', ') AS Content,\r\n" +
                "group_concat(DISTINCT e.AuthorName SEPARATOR ', ') AS Authors,\r\n" +
                "group_concat(DISTINCT f.ArtistName SEPARATOR ', ') AS Artists\r\n" +
                "\r\n" +
                "FROM  (SELECT * FROM manga WHERE manga.rating >= "+rating+") manga \r\n"+
                "INNER JOIN originallanguage h ON (manga.LanguageID=h.LanguageID "+getLanguage(language)+" )\r\n" +
                "INNER JOIN demographic i ON (manga.DemographicID=i.DemographicID "+getDemographic(demographic)+" )\r\n" +
                "INNER JOIN publicationstatus j ON (manga.PublicationStatusID=j.PublicationStatusID "+getPubStatus(pubStatus)+") \r\n"+

                "INNER JOIN (SELECT mangaauthor.MangaID ,author.AuthorName   FROM mangaauthor  INNER JOIN author  ON (mangaauthor.AuthorID = author.AuthorID "+getAuthor(author)+" ))     e ON (e.MangaID=manga.MangaID)\r\n" +
                "INNER JOIN (SELECT mangaartist.MangaID ,artist.ArtistName   FROM mangaartist  INNER JOIN artist  ON (mangaartist.ArtistID = artist.ArtistID "+getArtist(artist)+" ))     f ON (f.MangaID=manga.MangaID) \r\n"+

                "INNER JOIN alternativename g ON (manga.MangaID = g.MangaID "+getName(mangaName)+" )\r\n"+

                "LEFT JOIN (SELECT mangagenre.MangaID  ,genre.GenreName     FROM mangagenre   INNER JOIN genre   ON (mangagenre.GenreID = genre.GenreID))         a ON (a.MangaID=manga.MangaID)  \r\n" +
                "LEFT JOIN (SELECT mangatheme.MangaID  ,theme.ThemeName     FROM mangatheme   INNER JOIN theme   ON (mangatheme.ThemeID = theme.ThemeID))         b ON (b.MangaID=manga.MangaID)\r\n" +
                "LEFT JOIN (SELECT mangaformat.MangaID ,format.FormatName   FROM mangaformat  INNER JOIN format  ON (mangaformat.FormatID = format.FormatID))     c ON (c.MangaID=manga.MangaID) \r\n" +
                "LEFT JOIN (SELECT mangacontent.MangaID,content.ContentName FROM mangacontent INNER JOIN content ON (mangacontent.ContentID = content.ContentID)) d ON (d.MangaID=manga.MangaID)\r\n"+



                " GROUP BY manga.MangaID "+
                getHavings(tags)+
                " ORDER BY RAND() LIMIT 1;";

        // "(SELECT * FROM manga WHERE manga.rating >= "+rating+") manga"
        System.out.println();
        System.out.println();
        System.out.println(query);
        System.out.println();
        System.out.println();
        openGUI(query);
    }

    private String getName(String mangaName) {
        if (!mangaName.isEmpty()) {
            return " AND (manga.MangaName LIKE '%"+mangaName+"%' OR g.AlternativeName LIKE '%"+mangaName+"%' ) ";
        }
        return "";
    }


    private String getLanguage(String language) {
        if (!language.isEmpty()) {
            return " AND h.LanguageName = '"+language+"' ";
        }
        return "";
    }

    private String getDemographic(String demographic) {
        if (!demographic.isEmpty()) {
            return " AND i.DemographicName LIKE '"+demographic+"' ";
        }
        return "";
    }

    private String getPubStatus(String pubStatus) {
        if (!pubStatus.isEmpty()) {
            return " AND j.PublicationStatusName LIKE '"+pubStatus+"' ";
        }
        return "";
    }
    private String getAuthor(String author) {
        if (!author.isEmpty()) {
            return " AND author.AuthorName LIKE '%"+author+"%' ";
        }
        return "";
    }
    private String getArtist(String artist) {
        if (!artist.isEmpty()) {
            return " AND artist.ArtistName LIKE '%"+artist+"%' ";
        }
        return "";
    }

    private String getHavings(String tags) {
        if (!tags.isEmpty()) {
            return "HAVING "+searchTags.search(tags)+" ";

        }
        return "";
    }
    private void openGUI(String query) {
        EventQueue.invokeLater(() -> {
            try {
                ResultGUI gui = new ResultGUI(mySQL.query(query));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}