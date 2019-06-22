	package com.dj.busly.packet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dj.busly.packet.Packet.PacketJSON;
import com.dj.busly.trip.Trip;
import com.dj.busly.trip.TripService;
import com.dj.busly.utils.ResponseMessage;

@RestController
@RequestMapping("/packet")
public class PacketControlller {

	@Autowired
	private PacketService packetService;
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	PacketServiceImpl packetServiceImp;
	
	String filename=null;
	
	@RequestMapping(value="/savePacket", method=RequestMethod.POST)
	public ResponseMessage savePacket(@RequestBody PacketJSON packet) {
		System.out.println("savePacket");
		return packetService.savePacket(packet);
	}
	
//	@RequestMapping(name="/saveall/{tripId}", method=RequestMethod.POST)
//	public ResponseMessage saveallPackets(@PathVariable("tripId") String tripId,
//			@RequestBody List<PacketJSON> packets) {
//		return packetService.savePackets(packets);
//	}
	
	
	/**
	 * consumer side api for trip id
	 * @param tripId
	 * @return
	 */
	
	@RequestMapping(value="/getall/{tripId}", method = RequestMethod.GET)
	public List<Packet> getPackets(@PathVariable("tripId") String tripId) {
		System.out.println("getPackets"+tripId);
		 
		return packetService.getPackets(tripId);
	}
	
	@RequestMapping(value="/getPacket",method= RequestMethod.GET)
	public List<Packet> getPacket(){
		System.out.println("getPackets"+packetService.getPacket().toString());
		return packetService.getPacket();	
	}
	
	@RequestMapping(value="/getSinglePacket/{tripId}",method=RequestMethod.GET)
	public Packet getSinglePacket(@PathVariable("tripId") String tripId){
		
		return packetService.getLatestTripPacket(tripId);
		
	}
	
	@RequestMapping(value="/getPacketByTime/{tripId}/{time}",method=RequestMethod.GET)
	public List<Packet> getPacketsByTime(@PathVariable("tripId") String tripId,@PathVariable("time") String time){
		
		
		return packetService.getPacketsWithTime(tripId, time);
		
	}
	
	@RequestMapping(value="/uploadFile",method=RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException {
		
		String key="this is a my key";
		File convertFile = new File("/home/dj/Desktop/OwnCloudData/"+file.getOriginalFilename());
	    convertFile.createNewFile();
	      FileOutputStream fout = new FileOutputStream(convertFile);
	      fout.write(file.getBytes());
	      fout.close();
	      File outputFile= new File("/home/dj/Desktop/OwnCloudData/"+file.getOriginalFilename()+".encrypted");
	      filename=file.getOriginalFilename();
	      
	      packetServiceImp.fileProcessor(Cipher.ENCRYPT_MODE, key, convertFile, outputFile);
	      
	      convertFile.delete();
	      
	      return "File is upload successfully";
	   }
	
	 @RequestMapping(value = "/download", method = RequestMethod.GET) 
	   public ResponseEntity<Object> downloadFile() throws IOException  {
			String key="this is a my key";
		 
		 File encryptedFile = new File("/home/dj/Desktop/OwnCloudData/"+filename+".encrypted");
		 File decryptedFile = new File("/home/dj/Desktop/OwnCloudData/"+filename);
				
	      
	      packetServiceImp.fileProcessor(Cipher.DECRYPT_MODE, key, encryptedFile, decryptedFile);
	      
	      InputStreamResource resource = new InputStreamResource(new FileInputStream(decryptedFile));
	      HttpHeaders headers = new HttpHeaders();
	      
	      headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", decryptedFile.getName()));
	      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	      headers.add("Pragma", "no-cache");
	      headers.add("Expires", "0");
	      
	      ResponseEntity<Object> 
	      responseEntity = ResponseEntity.ok().headers(headers).contentLength(
	         decryptedFile.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
	      
	      return responseEntity;
	   }
	

	
}
