While(True)

   If(WinExists($CmdLine[1])) Then
	  WinActivate($CmdLine[1])
	  ControlSetText($CmdLine[1],"","[CLASS:Edit; INSTANCE:1]",$CmdLine[2])
	  ControlClick($CmdLine[1],"","[CLASS:Button; INSTANCE:1]")
	  ExitLoop
   EndIf

   Sleep(2000);wait for 2 seconds
WEnd