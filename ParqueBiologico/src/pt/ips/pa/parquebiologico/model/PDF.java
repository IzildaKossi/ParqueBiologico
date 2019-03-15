/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ips.pa.parquebiologico.model;

import java.io.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.*;
import pt.ips.pa.parquebiologico.dao.*;

/**
 *
 * @author
 */
public class PDF {

    private PDDocument document;
    private PDPage page;
    private ParqueBiologicoDAO utilizadorDAOSQLite;

    public PDF(ParqueBiologicoDAO utilizadorDAOSQLite, Object object) {
        this.utilizadorDAOSQLite = utilizadorDAOSQLite;
        this.document = new PDDocument();
        this.page = new PDPage();
        criarPdf(object);
    }

    private void criarPdf(Object object) {
        try {
            document.addPage(page);
            try (PDPageContentStream content = new PDPageContentStream(document, page)) {
                content.beginText();
                content.setFont(PDType1Font.TIMES_ROMAN, 12);
                //Setting the leading
                content.setLeading(14.5f);
                //Setting the position for the line
                content.newLineAtOffset(25, 725);

                if (object instanceof Bilhete) {
                    Bilhete bilhete = ((Bilhete) object);
                    content.showText(String.format(" --- BILHETE Nº %06d ---", bilhete.getNumBilhete()));
                    content.newLine();
                    content.showText("Data de Emissao: " + bilhete.getData().toString());
                    content.newLine();
                    content.showText("Hora de Emissao: " + bilhete.getHora().toString());
                    content.newLine();
                    content.showText("Nome Utilizador: " + bilhete.getUtilizador().getNome());
                    content.newLine();
                    content.showText("Nif de Utilizador: " + bilhete.getUtilizador().getNif());
                    content.newLine();
                    content.showText("Percurso: " + bilhete.getPercurso());

                } else if (object instanceof Fatura) {
                    Fatura fatura = ((Fatura) object);
                    content.showText(String.format(" --- FATURA Nº %06d ---", fatura.getNumFatura()));
                    content.newLine();
                    content.showText("Data de Emissao: " + fatura.getData().toString());
                    content.newLine();
                    content.showText("Hora de Emissao: " + fatura.getHora().toString());
                    content.newLine();
                    if (fatura.getUtilizadorNif() == 0) {
                        content.showText("Nif de Utilizador: N/A");
                    } else {
                        content.showText("Nif de Utilizador: " + fatura.getUtilizadorNif());
                    }
                }
                content.endText();
                content.close();
            }

            if (object instanceof Bilhete) {
                Bilhete bilhete = ((Bilhete) object);
                document.save("bilhete_" + String.format("%06d", bilhete.getNumBilhete()) + ".pdf");
            } else if (object instanceof Fatura) {
                Fatura fatura = ((Fatura) object);
                document.save("fatura_" + String.format("%06d", fatura.getNumFatura()) + ".pdf");
            }
            document.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ParqueBiologicoDAO getUtilizadorDAOSQLite() {
        return utilizadorDAOSQLite;
    }

}
