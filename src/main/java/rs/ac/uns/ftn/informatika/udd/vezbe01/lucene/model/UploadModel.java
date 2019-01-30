package rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.model;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.uns.ftn.informatika.udd.vezbe01.dto.CategoryDTO;
import rs.ac.uns.ftn.informatika.udd.vezbe01.dto.LanguageDTO;
import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;
//import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;

import java.io.Serializable;
import java.util.Arrays;

public class UploadModel implements Serializable {

    private Long id;
    private String title;
    private String author;
    private String keywords;
    private MultipartFile[] files;
    private Long categoryId;
    private CategoryDTO category;
    private Long languageId;
    private LanguageDTO language;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public MultipartFile[] getFiles() {
        return files;
    }
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getLanguageId() {
        return languageId;
    }
    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
    public LanguageDTO getLanguage() {
        return language;
    }
    public void setLanguage(LanguageDTO language) {
        this.language = language;
    }

    public UploadModel() {
    }

    public UploadModel(Long id, String title, String author, String keywords, String files, CategoryDTO category, LanguageDTO language) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.keywords = keywords;
//        this.files = files;
        this.category = category;
        this.language = language;
    }

    public UploadModel(UModel uModel) {
        this(uModel.getId(), uModel.getTitle(), uModel.getAuthor(), uModel.getKeywords(), uModel.getFiles(), new CategoryDTO(uModel.getCategory()), new LanguageDTO(uModel.getLanguage()));
    }

    @Override
    public String toString() {
        return " " +
                "Title: '" + title + '\'' +
                ", Author: '" + author + '\'' +
                ", Keywords: '" + keywords + '\'';
    }
}
