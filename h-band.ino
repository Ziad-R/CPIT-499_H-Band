
#define USE_ARDUINO_INTERRUPTS true    // Set-up low-level interrupts for most acurate BPM math.
#include <PulseSensorPlayground.h>     // Includes the PulseSensorPlayground Library.   

//  Pulse Variables
const int PulsePin = 0;       // PulseSensor PURPLE WIRE connected to ANALOG PIN 0
int Threshold = 550;           // Determine which Signal to "count as a beat" and which to ignore.

// Temp Variables
int value;
int tempPin = 1;
              
PulseSensorPlayground pulseSensor;  // Creates an instance of the PulseSensorPlayground object called "pulseSensor"


void setup() {   

  Serial.begin(9600);          // For Serial Monitor

  // Configure the PulseSensor object, by assigning our variables to it. 
  pulseSensor.analogInput(PulsePin);   

  pulseSensor.setThreshold(Threshold);   

  // Double-check the "pulseSensor" object was created and "began" seeing a signal. 
  pulseSensor.begin();
     
  
}



void loop() {
 // Display Temp
 value = analogRead(tempPin);
 float milliVolt = ( value/1024.0)*5000; 
 float tempC = milliVolt/10;
 Serial.print("TEMPRATURE = ");
 Serial.println(tempC);

 // Display Pulse
 int myBPM = pulseSensor.getBeatsPerMinute();  // Calls function on our pulseSensor object that returns BPM as an "int".

  if (pulseSensor.sawStartOfBeat()) {            // Constantly test to see if "a beat happened". 

  Serial.print("BPM: ");                        // Print phrase "BPM: " 
  Serial.println(myBPM);                        // Print the value inside of myBPM. 
  }

  delay(1000);                    // considered best practice in a simple sketch.

}

  
