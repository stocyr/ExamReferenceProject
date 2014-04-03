ExamReferenceProject
====================

Beispielprojekt für die Prüfung im Embedded Android Modul

# TODO

Jedes soll bei seiner lokalen Version im Kopf seinen Namen und den Hinweis auf die Prüfung schreiben!

# Elemente

Für die Prüfung müssen folgende Elemente eingesetzt werden können:

- Taster
- LEDs
- ADC
- TextView
- Timer
- Toasts
- (TextToSpeach)

### Taster und LEDs

Für die Taster und LEDs wird die Klasse `SysfsFileGPIO` verwendet. Aktuell wird nur die erste LED und der erste Taster verwendet.

Die LED übernimmt jeweils den gleichen Zustand wie der Taster. Ausserdem wird der Taster in einem 20Hz Timer-Interval (siehe weiter unten) auf positive Flanke analysiert und wenn positiv wird die Sprachausgabe gestartet.

*ACHTUNG: die Taster und LEDs sind alle LOW-ACTIVE! Diese Logik wurde von der Klasse SysfsFileGPIO noch nicht umgedreht.*

### ADC

Für den ADC wird die Klasse `ADC` verwendet. In einem 20Hz Timer-Interval wird dessen Wert (in LSBs) gelesen und so direkt auf einem TextView angezeigt (siehe unten).

### TextView

Ein `TextView` Objekt ist schon auf dem Layout erstellt und per @id in der main activity eingelesen. Es wurde per cast auf `TextView` in ein lokales Attribut gespeichert und kann in einem `runOnUiThread()` verändert werden.

### Button

Es wurde vorsichtshalber ebenfalls noch ein `Button`  installiert, der wie beim TextView (oben) als lokales Objekt verwendbar ist. Der `OnClickListener` zeigt ein Toast (siehe unten) an.

### Timer

Es wurden zwei `Timer` mit dazugehörigen `TimerTasks` (als innere Klassen) erstellt und jeweils mit 20Hz gescheduled. Darin werden momentan der ADC ausgelesen und die TextView aktualisiert und im zweiten Task der Button1 ausgelesen, dessen Wert direkt auf die LED daneben geschrieben und ebenfalls noch auf positive Flanke geprüft (mit einer Klassen-internen old-value variable).

Ausserdem wurde noch ein one-time-recurring Timer geschedulet, welcher 3 Sekunden nach App-Start ein Toast anzeigt.

### Toasts

Es wurden überall im App Toasts verteilt, welche Ausgaben anzeigen. 

*Wichtig: Sobald ein Toast nicht direkt in einer Methode der ` MainActivity` gestartet wird (also wenn z.B. der Timer-Scheduler den Toast starten will), muss er in einem `runOnUIThread()`-Konstrukt gestartet und das Objekt `context` (ist in der MainActivity als Attribut definiert) mitgegeben werden.*

### TextToSpeech

Die TextToSpeech Engine wird in einer `onInit()` Methode direkt während dem App-Start initialisiert. Momentan löst ein Tastendruck auf dem ersten Button die Sprachausgabe aus.
