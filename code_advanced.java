import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
public class diktua2 {
public static void main(String[] param) throws Exception {
String packetInfo = "E4078";
String packetTemp = "E4078T00";
int serverPort = 38038;
int clientPort = 48038;
String imageInfo1 = "M0871CAM=PTZ";
String imageInfo2 = "M0871CAM=FIX";
String AudioInfo = "A6531L33F400";
String audioAQDPCM = "A6531AQF400";
String generatorInfo = "A6531T400";
String ithakicopterInfo = "Q6517";
String packetInfozero = "E0000";
String EngineRunTime = "V8547OBD=01 1F";
String IthakiAirTemperature = "V8547OBD=01 0F";
String ThrottlePosition = "V8547OBD=01 11";
String EngineRPM = "V8547OBD=01 0C";
String VehicleSpeed = "V8547OBD=01 0D";
String CoolantTemperature = "V8547OBD=01 05";
// //ECHO
DatagramSocket s = new DatagramSocket();
byte[] txbuffer = packetInfo.getBytes();
byte[] hostIP = { (byte)155,(byte)207,(byte)18,(byte)208 };
InetAddress hostAddress = InetAddress.getByAddress(hostIP);
DatagramPacket p = new DatagramPacket(txbuffer,txbuffer.length,hostAddress,serverPort);
DatagramSocket r = new DatagramSocket(clientPort);
r.setSoTimeout(8000);
byte[] rxbuffer = new byte[2048];
DatagramPacket q = new DatagramPacket(rxbuffer,rxbuffer.length);
FileWriter Echopackets2= new FileWriter("Echopacks2.txt");
ArrayList<Long> echopacks2 = new ArrayList<>();
BufferedWriter out = new BufferedWriter(Echopackets2);
long startTime=System.currentTimeMillis();
ArrayList<Long> array = new ArrayList<>();
while(System.currentTimeMillis()-startTime<=240000) {
long t1=System.currentTimeMillis();
s.send(p);
try {
r.receive(q);
long t2=System.currentTimeMillis();
echopacks2.add(t2-t1);
} catch (Exception x) {System.out.println(x);}
long lastIndexTimeEcho = echopacks2.get(echopacks2.size()-1);
out.write(" \n "+lastIndexTimeEcho);
array.add(lastIndexTimeEcho);
}
FileWriter Rithmapodosi= new FileWriter("Rithmapodosi.txt");
BufferedWriter out2 = new BufferedWriter(Rithmapodosi);
FileWriter parathira = new FileWriter("parathira.txt");
BufferedWriter out3 = new BufferedWriter(parathira);
long sum = 0;
ArrayList<Integer> jArray = new ArrayList<>();
for(int j=0; j<array.size(); j++) {
if(sum+array.get(j) > 8000) {
out3.write(" \n "+j);
jArray.add(j);
sum = array.get(j);
}
else
{
sum = sum + array.get(j);
}
}
int R = 0;
int x1=0;
ArrayList<Integer> rithm = new ArrayList<>();
for(int i=0; i<jArray.size()-1; i++) {
x1=jArray.get(i+1)-jArray.get(i);
rithm.add(x1);
x1=0;
}
for(int i=0; i<rithm.size(); i++) {
R=32*rithm.get(i)*8/8;
out2.write(" \n "+R);
R=0;
}
echopacks2.clear();
out.close();
out2.close();
out3.close();
//Echo thermopkrasies
byte[] txechoTemp = packetTemp.getBytes();
byte[] rxechoTemp = new byte[2048];
DatagramPacket qechoTemp = new DatagramPacket(rxechoTemp,rxechoTemp.length);
DatagramPacket pechoTemp = new DatagramPacket(txechoTemp,txechoTemp.length,hostAddress,serverPort);
s.send(pechoTemp);
try {
r.receive(qechoTemp);
String message = new String(rxechoTemp,0,qechoTemp.getLength());
System.out.println(message);
} catch (Exception x) {System.out.println(x);}
//ECHO 0000
byte[] txbufferzero = packetInfozero.getBytes();
DatagramPacket pzero = new DatagramPacket(txbufferzero,txbufferzero.length,hostAddress,serverPort);
byte[] rxbufferzero = new byte[2048];
DatagramPacket qzero = new DatagramPacket(rxbufferzero,rxbufferzero.length);
FileWriter Echopackets2zero= new FileWriter("Echopacks2zero.txt");
ArrayList<Long> echopacks2zero = new ArrayList<>();
BufferedWriter outzero = new BufferedWriter(Echopackets2zero);
long startTimezero=System.currentTimeMillis();
ArrayList<Long> arrayzero = new ArrayList<>();
while(System.currentTimeMillis()-startTimezero<=240000) {
long t1zero=System.currentTimeMillis();
s.send(pzero);
try {
r.receive(qzero);
long t2zero=System.currentTimeMillis();
echopacks2zero.add(t2zero-t1zero);
} catch (Exception x) {System.out.println(x);}
long lastIndexTimeEchozero = echopacks2zero.get(echopacks2zero.size()-1);
outzero.write(" \n "+lastIndexTimeEchozero);
arrayzero.add(lastIndexTimeEchozero);
}
FileWriter Rithmapodosizero= new FileWriter("Rithmapodosizero.txt");
BufferedWriter out2zero = new BufferedWriter(Rithmapodosizero);
FileWriter parathirazero = new FileWriter("parathirazero.txt");
BufferedWriter out3zero = new BufferedWriter(parathirazero);
long sumzero = 0;
ArrayList<Integer> jArrayzero = new ArrayList<>();
for(int j=0; j<arrayzero.size(); j++) {
if(sumzero+arrayzero.get(j) > 8000) {
out3zero.write(" \n "+j);
jArrayzero.add(j);
sumzero = arrayzero.get(j);
}
else
{
sumzero = sumzero + arrayzero.get(j);
}
}
int Rzero = 0;
int xzero=0;
ArrayList<Integer> rithmzero = new ArrayList<>();
for(int i=0; i<jArrayzero.size()-1; i++) {
xzero=jArrayzero.get(i+1)-jArrayzero.get(i);
rithmzero.add(xzero);
xzero=0;
}
for(int i=0; i<rithmzero.size(); i++) {
Rzero=32*rithmzero.get(i)*8/8;
out2zero.write(" \n "+Rzero);
Rzero=0;
}
echopacks2zero.clear();
outzero.close();
out2zero.close();
out3zero.close();
for(int i=0; i<5; i++) {
s.send(p);
try {
r.receive(q);
} catch (Exception x) {break;}
}
// IMAGE (1)
byte[] tximagebuffer = imageInfo1.getBytes();
DatagramPacket pimage = new DatagramPacket(tximagebuffer,tximagebuffer.length,hostAddress,serverPort);
byte[] rximagebuffer = new byte[2048];
DatagramPacket qimage = new DatagramPacket(rximagebuffer,rximagebuffer.length);
ArrayList<Byte> ImageData = new ArrayList<Byte>();
s.send(pimage);
for(int j=0;j<1000;j++) {
try {
r.receive(qimage);
String messagei = new String(rximagebuffer,0,qimage.getLength());
// System.out.println("\n"+j+" "+qimage.getLength());
if(qimage.getLength() < 128) {break;}
for(int i=0; i<qimage.getLength(); i++)
ImageData.add(rximagebuffer[i]);
} catch (Exception ex) {System.out.println(ex);}
}
byte[] imageArray = new byte[ImageData.size()];
for(int i=0; i<imageArray.length; i++)
imageArray[i] = ImageData.get(i);
try {
ByteArrayInputStream image= new ByteArrayInputStream(imageArray);
BufferedImage finalimage = ImageIO.read(image);
ImageIO.write(finalimage , "jpg", new File("Image1.jpg") );
System.out.println("\nConverted Successfully!");
} catch(Exception x) {System.out.println(x);}
ImageData.clear();
// IMAGE (2)
byte[] tximage2 = imageInfo2.getBytes();
DatagramPacket pimage2 = new DatagramPacket(tximage2,tximage2.length,hostAddress,serverPort);
byte[] rximage2 = new byte[2048];
DatagramPacket qimage2 = new DatagramPacket(rximage2,rximage2.length);
ArrayList<Byte> ImageData2 = new ArrayList<Byte>();
s.send(pimage2);
for(int j=0;j<1000;j++) {
try {
r.receive(qimage2);
// String messagei2 = new String(rximage2,0,qimage2.getLength());
if(qimage2.getLength() < 128) {break;}
} catch (Exception ex) {System.out.println(ex);break;}
for(int i=0; i<qimage2.getLength(); i++)
ImageData2.add(rximage2[i]);
}
byte[] imageArray2 = new byte[ImageData2.size()];
for(int i=0; i<imageArray2.length; i++)
imageArray2[i] = ImageData2.get(i);
try {
ByteArrayInputStream image2= new ByteArrayInputStream(imageArray2);
BufferedImage finalimage2 = ImageIO.read(image2);
ImageIO.write(finalimage2 , "jpeg", new File("Image2.jpeg") );
System.out.println("\nConverted Successfully!");
} catch(Exception x) {System.out.println(x);}
ImageData2.clear();
for(int i=0; i<5; i++) {
s.send(p);
try {
r.receive(q);
} catch (Exception x) {break;}
}
//Vehicle
byte[] txEngineRunTime = EngineRunTime.getBytes();
byte[] txIthakiAirTemperature = IthakiAirTemperature.getBytes();
byte[] txThrottlePosition = ThrottlePosition.getBytes();
byte[] txEngineRPM = EngineRPM.getBytes();
byte[] txVehicleSpeed = VehicleSpeed.getBytes();
byte[] txCoolantTemperature = CoolantTemperature.getBytes();
byte[] rxvehicle = new byte[2048];
DatagramPacket qvehicle = new DatagramPacket(rxvehicle,rxvehicle.length);
DatagramPacket pEngineRunTime = new DatagramPacket(txEngineRunTime,txEngineRunTime.length, hostAddress,serverPort);
DatagramPacket pIthakiAirTemperature = new DatagramPacket(txIthakiAirTemperature,txIthakiAirTemperature.length, hostAddress,serverPort);
DatagramPacket pTrhrottlePosition = new DatagramPacket(txThrottlePosition,txThrottlePosition.length, hostAddress,serverPort);
DatagramPacket pEngineRPM = new DatagramPacket(txEngineRPM,txEngineRPM.length, hostAddress,serverPort);
DatagramPacket pVehicleSpeed = new DatagramPacket(txVehicleSpeed,txVehicleSpeed.length, hostAddress,serverPort);
DatagramPacket pCoolantTemperature = new DatagramPacket(txCoolantTemperature,txCoolantTemperature.length, hostAddress,serverPort);
byte[] bytesarray = new byte[2];
FileWriter Engineruntime = new FileWriter("EngineRunTime.txt");
BufferedWriter EngineRunTimetxt = new BufferedWriter(Engineruntime);
FileWriter Ithakeairtemperature = new FileWriter("IthakiAirTemperature.txt");
BufferedWriter IthakiAirTemperaturetxt= new BufferedWriter(Ithakeairtemperature);
FileWriter Throttleposition = new FileWriter("ThrottlePosition.txt");
BufferedWriter ThrottlePositiontxt = new BufferedWriter(Throttleposition);
FileWriter Enginerpm = new FileWriter("EngineRPM.txt");
BufferedWriter EngineRPMtxt = new BufferedWriter(Enginerpm);
FileWriter Vehiclespeed = new FileWriter("VehicleSpeed.txt");
BufferedWriter Vehiclespeedtxt = new BufferedWriter(Vehiclespeed);
FileWriter Coolanttemperature = new FileWriter("CoolantTemperature.txt");
BufferedWriter Coolanttemperaturetxt = new BufferedWriter(Coolanttemperature);
long vehicleStart = System.currentTimeMillis();
while(System.currentTimeMillis()- vehicleStart <=240000) {
s.send(pEngineRunTime);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
bytesarray[1] = (byte) Integer.parseInt(spasimo[3],16);
EngineRunTimetxt.write("\n"+ (256* bytesarray[0]+bytesarray[1]));
} catch (Exception x) {System.out.println(x);}
s.send(pIthakiAirTemperature);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
IthakiAirTemperaturetxt.write("\n"+ (bytesarray[0] -40));
} catch (Exception x) {System.out.println(x);}
s.send(pTrhrottlePosition);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
ThrottlePositiontxt.write("\n"+ (bytesarray[0]*100/255));
} catch (Exception x) {System.out.println(x);}
s.send(pEngineRPM);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
bytesarray[1] = (byte) Integer.parseInt(spasimo[3],16);
EngineRPMtxt.write("\n"+ ((256*bytesarray[0]+bytesarray[1])/4));
} catch (Exception x) {System.out.println(x);}
s.send(pVehicleSpeed);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
Vehiclespeedtxt.write("\n"+ bytesarray[0]);
} catch (Exception x) {System.out.println(x);}
s.send(pCoolantTemperature);
try {
r.receive(qvehicle);
String m = new String(rxvehicle,0,qvehicle.getLength());
String[] spasimo = m.split(" ");
bytesarray[0] = (byte) Integer.parseInt(spasimo[2],16);
Coolanttemperaturetxt.write("\n"+ (bytesarray[0]-40));
} catch (Exception x) {System.out.println(x);}
}
EngineRunTimetxt.close();
IthakiAirTemperaturetxt.close();
ThrottlePositiontxt.close();
EngineRPMtxt.close();
Vehiclespeedtxt.close();
Coolanttemperaturetxt.close();
for(int i=0; i<5; i++) {
s.send(p);
try {
r.receive(q);
} catch (Exception x) {break;}
}
//Audio
//Generator
byte[] txgenerator = generatorInfo.getBytes();
DatagramPacket pgenerator = new DatagramPacket(txgenerator,txgenerator.length, hostAddress,serverPort);
byte[] rxgenerator = new byte[128];
DatagramPacket qgenerator = new DatagramPacket(rxgenerator,rxgenerator.length);
ArrayList<Byte> ByteGenerator = new ArrayList<Byte>();
s.send(pgenerator);
for(int i=0; i<400; i++) {
r.receive(qgenerator);
for(int j=0; j<rxgenerator.length; j++) {
int sample1 = (((240 & rxgenerator[j])>>4)-8);
int sample2 = ((15 & rxgenerator[j])-8);
if (ByteGenerator.size() ==0)
ByteGenerator.add((byte)sample1);
else
ByteGenerator.add((byte)(sample1 +(int)(ByteGenerator.get(ByteGenerator.size()-1))));
ByteGenerator.add((byte)(sample2 +(int)(ByteGenerator.get(ByteGenerator.size()-1))));
}
}
AudioFormat linearGen = new AudioFormat(8000,8,1,true,false);
SourceDataLine lineOutgen =null;
lineOutgen = AudioSystem.getSourceDataLine(linearGen);
FileWriter generatortxt = new FileWriter("generator.txt");
BufferedWriter generatorWritter = new BufferedWriter(generatortxt);
byte[] genout = new byte[ByteGenerator.size()];
lineOutgen .open(linearGen,32000);
lineOutgen .start();
for(int i=0; i<ByteGenerator.size(); i++) {
genout[i] = ByteGenerator.get(i);
generatorWritter.write("\n"+genout[i]);
}
generatorWritter.close();
lineOutgen .write(genout,0,genout.length);
lineOutgen .stop();
lineOutgen .close();
// DPCM
byte[] txDPCM = AudioInfo.getBytes();
DatagramPacket pDPCM = new DatagramPacket(txDPCM,txDPCM.length, hostAddress,serverPort);
byte[] rxDPCM = new byte[128];
DatagramPacket qDPCM = new DatagramPacket(rxDPCM,rxDPCM.length);
ArrayList<Byte> ByteDPCM = new ArrayList<Byte>();
FileWriter DPCMtxt = new FileWriter("DPCM.txt");
BufferedWriter DPCMwritter = new BufferedWriter(DPCMtxt);
s.send(pDPCM);
for(int i=0; i<400; i++) {
r.receive(qDPCM);
for(int j=0; j<rxDPCM.length; j++) {
int sample1 = (((240 & rxDPCM[j])>>4)-8)*2;
int sample2 = ((15 & rxDPCM[j])-8)*2;
if (ByteDPCM.size() ==0)
ByteDPCM.add((byte)sample1);
else
ByteDPCM.add((byte)(sample1 +(int)(ByteDPCM.get(ByteDPCM.size()-1))));
ByteDPCM.add((byte)(sample2 +(int)(ByteDPCM.get(ByteDPCM.size()-1))));
}
}
AudioFormat linearDPCM = new AudioFormat(8000,8,1,true,false);
SourceDataLine lineOutDPCM =null;
byte[] audioBufferOutDPCM = new byte[ByteDPCM.size()];
lineOutDPCM = AudioSystem.getSourceDataLine(linearDPCM);
lineOutDPCM.open(linearDPCM,32000);
lineOutDPCM.start();
for(int i=0; i<ByteDPCM.size(); i++) {
audioBufferOutDPCM[i] = ByteDPCM.get(i);
DPCMwritter.write("\n"+audioBufferOutDPCM[i]);
}
FileWriter DPCMdiff = new FileWriter("DPCMdiff.txt");
BufferedWriter DPCMdifftxt = new BufferedWriter(DPCMdiff);
for(int i=1; i<ByteDPCM.size(); i++)
DPCMdifftxt.write("\n"+(audioBufferOutDPCM[i]-audioBufferOutDPCM[i-1]));
DPCMwritter.close();
DPCMdifftxt.close();
lineOutDPCM.write(audioBufferOutDPCM,0,audioBufferOutDPCM.length);
lineOutDPCM.stop();
lineOutDPCM.close();
//AQDPCM
byte[] txAQDPCM = audioAQDPCM.getBytes();
DatagramPacket pAQDPCM = new DatagramPacket(txAQDPCM,txAQDPCM.length, hostAddress,serverPort);
byte[] rxAQDPCM = new byte[132];
DatagramPacket qAQDPCM = new DatagramPacket(rxAQDPCM,rxAQDPCM.length);
FileWriter mesosOrostxt = new FileWriter("mesosOros.txt");
BufferedWriter mesosOrosWritter = new BufferedWriter(mesosOrostxt);
FileWriter vimatxt = new FileWriter("vima.txt");
BufferedWriter vimaWritter = new BufferedWriter(vimatxt);
FileWriter diaforatxt = new FileWriter("diafora.txt");
BufferedWriter diaforaWritter = new BufferedWriter(diaforatxt);
FileWriter AQDPCMtxt = new FileWriter("AQDPCM.txt");
BufferedWriter AQDPCMWritter = new BufferedWriter(AQDPCMtxt);
ArrayList<Byte> AQDPCMnew = new ArrayList<Byte>();
int sample1 = 0;
int sample2 = 0;
s.send(pAQDPCM);
for(int j=0; j<400; j++) {
try {
r.receive(qAQDPCM);
short mesosOros = (short )( ((rxAQDPCM[1] & 0x00FF) << 8) | (rxAQDPCM[0]& 0x00FF) );
mesosOrosWritter.write( mesosOros + "\n");
int vima = (int)( ((rxAQDPCM[3] & 0x00FF) << 8) | (rxAQDPCM[2]& 0x00FF));
vimaWritter.write("\n" +vima);
for(int i=4; i<rxAQDPCM.length; i++) {
sample1 = (int)(((rxAQDPCM[i]>>>4) & 0x0000000F)-8);
diaforaWritter.write("\n" +sample1);
sample1 = sample1 *vima + mesosOros;
sample1 = sample1 + sample2;
AQDPCMWritter.write("\n" + sample1);
sample2 = (int)(( (int)(rxAQDPCM[i] & 0x0000000F)-8));
diaforaWritter.write("\n" + sample2);
sample2 = sample2 *vima + mesosOros ;
sample2 = sample2+ sample1;
AQDPCMWritter.write("\n" + sample2);
int sample1a = (int)((sample1 & 0x00FF));
AQDPCMnew.add((byte)(sample1a));
int sample1b = (int)((sample1 & 0xFF00) >>>8);
AQDPCMnew.add((byte)(sample1b));
int sample2a = (int)((sample2 & 0x00FF));
AQDPCMnew.add((byte)(sample2a));
int sample2b = (int)((sample2 & 0xFF00) >>>8);
AQDPCMnew.add((byte)(sample2b));
}
}catch (Exception x) {break;}
}
AudioFormat linearAQDPCM = new AudioFormat(8000,16,1,true,false);
SourceDataLine lineOutAQDPCM =null;
lineOutAQDPCM = AudioSystem.getSourceDataLine(linearAQDPCM);
lineOutAQDPCM.open(linearAQDPCM,32000);
lineOutAQDPCM.start();
byte[] OutAQDPCM = new byte[AQDPCMnew.size()];
for(int i=0; i<AQDPCMnew.size(); i++) {
OutAQDPCM[i] = AQDPCMnew.get(i);
}
AQDPCMWritter.close();
diaforaWritter.close();
lineOutAQDPCM.write(OutAQDPCM,0,OutAQDPCM.length);
lineOutAQDPCM.stop();
lineOutAQDPCM.close();
mesosOrosWritter.close();
vimaWritter.close();
System.out.println("\nNow you can start to play with ithakiCopter.");
for(int i=0; i<5; i++) {
s.send(p);
try {
r.receive(q);
} catch (Exception x) {break;}
}
String enter;
Scanner S = new Scanner(System.in);
System.out.println("Press Enter.");
enter=S.nextLine();
System.out.println(enter);
//ITHAKI COPTER
byte[] txithakicopterbuffer = ithakicopterInfo.getBytes();
DatagramPacket pithakicopter = new DatagramPacket(txithakicopterbuffer,txithakicopterbuffer.length, hostAddress,serverPort);
byte[] rxithakicopterbuffer = new byte[2048];
DatagramPacket qithakicopter = new DatagramPacket(rxithakicopterbuffer,rxithakicopterbuffer.length);
DatagramSocket rithakicopter = new DatagramSocket(48078);
String altitude = "";
rithakicopter.setSoTimeout(8000);
FileWriter Altitude = new FileWriter("altitude.txt");
BufferedWriter Altitudetxt = new BufferedWriter(Altitude);
s.send(pithakicopter);
for(;;) {
try {
rithakicopter.receive(qithakicopter);
String message = new String(rxithakicopterbuffer,0,qithakicopter.getLength());
System.out.println(message);
for(int j=64; j<67; j++)
altitude = altitude + message.charAt(j);
System.out.println(altitude);
Altitude.write("\n"+altitude);
} catch (Exception x) {System.out.println(x);}
if(altitude.equals("077") ) {
System.out.println("It's over.");
break;
}
else {altitude = "";}
}
Altitude.close();
// //AQDPCM2
byte[] txAQDPCM2 = audioAQDPCM.getBytes();
DatagramPacket pAQDPCM2 = new DatagramPacket(txAQDPCM2,txAQDPCM2.length, hostAddress,serverPort);
byte[] rxAQDPCM2 = new byte[132];
DatagramPacket qAQDPCM2 = new DatagramPacket(rxAQDPCM2,rxAQDPCM2.length);
FileWriter mesosOrostxt2 = new FileWriter("mesosOros2.txt");
BufferedWriter mesosOrosWritter2 = new BufferedWriter(mesosOrostxt2);
FileWriter vimatxt2 = new FileWriter("vima2.txt");
BufferedWriter vimaWritter2 = new BufferedWriter(vimatxt2);
FileWriter diaforatxt2 = new FileWriter("diafora2.txt");
BufferedWriter diaforaWritter2 = new BufferedWriter(diaforatxt2);
FileWriter AQDPCMtxt2 = new FileWriter("AQDPCM2.txt");
BufferedWriter AQDPCMWritter2 = new BufferedWriter(AQDPCMtxt2);
ArrayList<Byte> AQDPCMnew2 = new ArrayList<Byte>();
int sample12 = 0;
int sample22 = 0;
s.send(pAQDPCM2);
for(int j=0; j<400; j++) {
try {
r.receive(qAQDPCM2);
short mesosOros2 = (short )( ((rxAQDPCM2[1] & 0x00FF) << 8) | (rxAQDPCM2[0]& 0x00FF) );
mesosOrosWritter2.write( mesosOros2 + "\n");
int vima2 = (int)( ((rxAQDPCM2[3] & 0x00FF) << 8) | (rxAQDPCM2[2]& 0x00FF));
vimaWritter2.write("\n" +vima2);
for(int i=4; i<rxAQDPCM2.length; i++) {
sample12 = (int)(((rxAQDPCM2[i]>>>4) & 0x0000000F)-8);
diaforaWritter2.write("\n" +sample12);
sample12 = sample12 *vima2 + mesosOros2;
sample12 = sample12 + sample22;
AQDPCMWritter2.write("\n" + sample12);
sample22 = (int)(( (int)(rxAQDPCM2[i] & 0x0000000F)-8));
diaforaWritter2.write("\n" + sample22);
sample22 = sample22 *vima2 + mesosOros2 ;
sample22 = sample22+ sample12;
AQDPCMWritter2.write("\n" + sample22);
int sample12a = (int)((sample12 & 0x00FF));
AQDPCMnew2.add((byte)(sample12a));
int sample12b = (int)((sample12 & 0xFF00) >>>8);
AQDPCMnew2.add((byte)(sample12b));
int sample22a = (int)((sample22 & 0x00FF));
AQDPCMnew2.add((byte)(sample22a));
int sample22b = (int)((sample22 & 0xFF00) >>>8);
AQDPCMnew2.add((byte)(sample22b));
}
}catch (Exception x) {break;}
}
AudioFormat linearAQDPCM2 = new AudioFormat(8000,16,1,true,false);
SourceDataLine lineOutAQDPCM2 =null;
lineOutAQDPCM2 = AudioSystem.getSourceDataLine(linearAQDPCM2);
lineOutAQDPCM2.open(linearAQDPCM2,32000);
lineOutAQDPCM2.start();
byte[] OutAQDPCM2 = new byte[AQDPCMnew2.size()];
for(int i=0; i<AQDPCMnew2.size(); i++) {
OutAQDPCM2[i] = AQDPCMnew2.get(i);
}
AQDPCMWritter2.close();
diaforaWritter2.close();
lineOutAQDPCM2.write(OutAQDPCM2,0,OutAQDPCM2.length);
lineOutAQDPCM2.stop();
lineOutAQDPCM2.close();
mesosOrosWritter2.close();
vimaWritter2.close();
String enter2;
Scanner S2 = new Scanner(System.in);
System.out.println("Press Enter again.");
enter2=S2.nextLine();
System.out.println(enter2);
//IthakiCopter2
byte[] txithakicopterbuffer2 = ithakicopterInfo.getBytes();
DatagramPacket pithakicopter2 = new DatagramPacket(txithakicopterbuffer2,txithakicopterbuffer2.length, hostAddress,serverPort);
byte[] rxithakicopterbuffer2 = new byte[2048];
DatagramPacket qithakicopter2 = new DatagramPacket(rxithakicopterbuffer2,rxithakicopterbuffer2.length);
DatagramSocket rithakicopter2 = new DatagramSocket(48078);
String altitude2 = "";
rithakicopter2.setSoTimeout(8000);
FileWriter Altitude2 = new FileWriter("altitude2.txt");
BufferedWriter Altitudetxt2 = new BufferedWriter(Altitude2);
s.send(pithakicopter2);
for(;;) {
try {
rithakicopter2.receive(qithakicopter2);
String message2 = new String(rxithakicopterbuffer2,0,qithakicopter2.getLength());
System.out.println(message2);
for(int j=64; j<67; j++)
altitude2 = altitude2 + message2.charAt(j);
System.out.println(altitude2);
Altitude2.write("\n"+altitude2);
} catch (Exception x) {System.out.println(x);}
if(altitude2.equals("077") ) {
System.out.println("It's over.");
break;
}
else {altitude2 = "";}
}
Altitude2.close();
s.close();
r.close();
}
}