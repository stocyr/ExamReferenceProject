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

Für die Taster und LEDs wird die Klasse `SysfsFileGPIO` verwendet.

### ADC

Für den ADC wird die Klasse `ADC` verwendet.

### TextView

Ein `TextView` Objekt ist schon auf dem Layout erstellt und per id in der main activity eingelesen. Es wurde per cast auf `TextView` in ein lokales Attribut gespeichert und kann in einem `runOnUiThread()` verändert werden.

### Timer

Es wurden zwei `Timer` mit dazugehörigen `TimerTasks` (als innere Klassen) erstellt und jeweils mit 20Hz gescheduled. Darin werden momentan der ADC ausgelesen und die TextView aktualisiert und im zweiten Task der Button1 ausgelesen und dessen Wert direkt auf die LED daneben geschrieben.

### Toasts

*still to do*

### TextToSpeech

*still to do*
