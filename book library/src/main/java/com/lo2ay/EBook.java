package com.lo2ay;

public class EBook extends Book{
    private String FileFormat;

    public EBook(String title, int isbn, String author, String fileFormat) {
        super(title, isbn, author);
        this.FileFormat = fileFormat;
    }

    public String getFileFormat() {
        return FileFormat;
    }

    public void setFileFormat(String fileFormat) {
        if (fileFormat.equalsIgnoreCase("PDF")|| fileFormat.equalsIgnoreCase("EPUP"))
        FileFormat = fileFormat;
        else
            System.out.println(" file format not available");
    }
    @Override
    public void displayInfo()
    {
        super.displayInfo();
        System.out.println("file format"+ this.FileFormat);
    }
}
