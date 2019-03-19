
#define USE_ARDUINO_INTERRUPTS true    // Set-up low-level interrupts for most acurate BPM math.
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   
#include <SoftwareSerial.h>
#define RX 10
#define TX 11

//WiFi Variables
String AP = "Android-AP";
String PASS = "70707070";
String API = "2X2K8L7Y8T4FI1ND";
String field1 = "pulse";
String field2 = "temp";
SoftwareSerial esp8266(RX,TX);

//  Pulse Variables
const int PulsePin = 0;
int Threshold = 550;           // Determine which Signal to "count as a beat" and which to ignore.

// Temp Variables
int value;
int tempPin = 1;
              
PulseSensorPlayground pulseSensor;  // Creates an instance of the PulseSensorPlayground object called "pulseSensor"


void setup() {   
  esp8266.begin(115200);
  sendCommand("AT",5,"OK");
  sendCommand("AT+CWMODE=1",5,"OK");
  sendCommand("AT+CWJAP=\""+ AP +"\",\""+ PASS +"\"",20,"OK");
  
  Serial.begin(9600);          // For Serial Monitor

  // Configure the PulseSensor object, by assigning our variables to it. 
  pulseSensor.analogInput(PulsePin);   

  pulseSensor.setThreshold(Threshold);   
 
  pulseSensor.begin();
     
}



void loop() {
 // Send Temp
 value = analogRead(tempPin);
 float milliVolt = ( value/1024.0)*5000; 
 float tempC = milliVolt/10;
 Serial.print("TEMPRATURE = ");
 Serial.println(tempC);
  
 String getData = "GET /update?api_key="+ API +"&"+ field2 +"="+String(value);
 sendCommand("AT+CIPMUX=1",5,"OK");
 sendCommand("AT+CIPSTART=0,\"TCP\",\""+ HOST +"\","+ PORT,15,"OK");
 sendCommand("AT+CIPSEND=0," +String(getData.length()+4),4,">");
 esp8266.println(getData);
  
 // Send Pulse
 int myBPM = pulseSensor.getBeatsPerMinute();  // Calls function on our pulseSensor object that returns BPM as an "int".

  if (pulseSensor.sawStartOfBeat()) {            // Constantly test to see if "a beat happened". 

  getData = "GET /update?api_key="+ API +"&"+ field1 +"="+String(value);
  sendCommand("AT+CIPSEND=0," +String(getData.length()+4),4,">");
  esp8266.println(getData);
    
  }
  
  sendCommand("AT+CIPCLOSE=0",5,"OK");
  delay(1000);

}

  
