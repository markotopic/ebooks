package rs.ac.uns.ftn.informatika.udd.vezbe01.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import rs.ac.uns.ftn.informatika.udd.vezbe01.entity.UModel;
import rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.indexing.Indexer;
import rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.model.IndexUnit;
import rs.ac.uns.ftn.informatika.udd.vezbe01.lucene.model.UploadModel;
import rs.ac.uns.ftn.informatika.udd.vezbe01.repository.LanguageService;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.CategoryService;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.CategoryServiceInterface;
import rs.ac.uns.ftn.informatika.udd.vezbe01.service.UmodelServiceInterface;

@RestController
public class IndexerController {

        public Long caId;
        public Long langId;

		private static String DATA_DIR_PATH;
	
		static {
			ResourceBundle rb=ResourceBundle.getBundle("application");
			DATA_DIR_PATH=rb.getString("dataDir");
		}
		
		@GetMapping("/reindex")
		public ResponseEntity<String> index() throws IOException {
			File dataDir = getResourceFilePath(DATA_DIR_PATH);
			long start = new Date().getTime();
			int numIndexed = Indexer.getInstance().index(dataDir);
			long end = new Date().getTime();
			String text = "Indexing " + numIndexed + " files took "
					+ (end - start) + " milliseconds";
			return new ResponseEntity<String>(text, HttpStatus.OK);
		}
		
		private File getResourceFilePath(String path) {
		    URL url = this.getClass().getClassLoader().getResource(path);
		    File file = null;
		    try {
		        file = new File(url.toURI());
		    } catch (URISyntaxException e) {
		        file = new File(url.getPath());
		    }   
		    return file;
		}
		
	    @PostMapping("/index/add")
	    public ResponseEntity<String> multiUploadFileModel(@ModelAttribute UploadModel model) {

	        try {

	        	indexUploadedFile(model);
				saveDB(model);

	        } catch (IOException e) {
	            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity<String>("Successful uploaded book:" + model.toString(), HttpStatus.OK);

	    }

	    // ***Metadata***
        @PostMapping("/getmeta")
        public ResponseEntity<String> getMetadata(@ModelAttribute UploadModel model) {

            try {

                indexUploadedFile1(model);

            } catch (IOException e) {
                return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<String>("Metadata:" + model.toString(), HttpStatus.OK);

        }
        private void indexUploadedFile1(UploadModel model) throws IOException{

            for (MultipartFile file : model.getFiles()) {

                if (file.isEmpty()) {
                    continue; //next please
                }
                String fileName = saveUploadedFile(file);
                if(fileName != null){
                    IndexUnit indexUnit = Indexer.getInstance().getHandler(fileName).getIndexUnit(new File(fileName));
                    Indexer.getInstance().add(indexUnit.getLuceneDocument());
                }
            }
        }
        // ***Metadata***
		    
		    
	    //save file
	    private String saveUploadedFile(MultipartFile file) throws IOException {
	    	String retVal = null;
            if (! file.isEmpty()) {
	            byte[] bytes = file.getBytes();
	            Path path = Paths.get(getResourceFilePath(DATA_DIR_PATH).getAbsolutePath() + File.separator + file.getOriginalFilename());
	            Files.write(path, bytes);
	            retVal = path.toString();
            }
            return retVal;
	    }

	    @Autowired
	    LanguageService languageService;
	    
	    private void indexUploadedFile(UploadModel model) throws IOException{
	    	
	    	for (MultipartFile file : model.getFiles()) {

	            if (file.isEmpty()) {
	                continue; //next please
	            }
	            String fileName = saveUploadedFile(file);
	            if(fileName != null){
	            	IndexUnit indexUnit = Indexer.getInstance().getHandler(fileName).getIndexUnit(new File(fileName));
	            	indexUnit.setTitle(model.getTitle());
	            	indexUnit.setAuthor(model.getAuthor());
	            	indexUnit.setKeywords(new ArrayList<String>(Arrays.asList(model.getKeywords().split(" "))));

	            	setLangId(model.getLanguageId());
	            	indexUnit.setLanguage(languageService.findOne(langId).getName());

	            	Indexer.getInstance().add(indexUnit.getLuceneDocument());
	            }
	    	}
	    }

	    @Autowired
	    UmodelServiceInterface umodelServiceInterface;

		@Autowired
		CategoryServiceInterface categoryServiceInterface;

		@Autowired
		CategoryService categoryService;



        public Long getCaId() {
            return caId;
        }
        public void setCaId(Long caId) {
            this.caId = caId;
        }

        private void saveDB(UploadModel model) {

                UModel uModel = new UModel();
                uModel.setTitle(model.getTitle());
                uModel.setAuthor(model.getAuthor());
                uModel.setKeywords(model.getKeywords());
                uModel.setFiles(model.getFiles().toString());

                setCaId(model.getCategoryId());
                uModel.setCategory(categoryService.findOne(caId));
                setLangId(model.getLanguageId());
                uModel.setLanguage(languageService.findOne(langId));

                uModel = umodelServiceInterface.save(uModel);

            }

        public Long getLangId() {
            return langId;
        }

        public void setLangId(Long langId) {
            this.langId = langId;
        }

}
