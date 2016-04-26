package com.isiav.ctrl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
*
* @author outofmemory.cn
*/
public class PasteKit {

/**
* 显示剪贴板中的文本值
*/
public static String displayTextFromClipboard() {

Toolkit toolkit = Toolkit.getDefaultToolkit();
Clipboard clipboard = toolkit.getSystemClipboard();
Transferable tran = null;

try {

//The parameter to getContents is not currently used so null should
//be sent. If the clipboard is currently not available (for example
//it is used by another application) the method throws an
//IllegalStateException.
tran = clipboard.getContents(null);

} catch (IllegalStateException ex) {
System.out.println("The clipboard is unavailable.");
}

if (tran != null &&
tran.isDataFlavorSupported(DataFlavor.stringFlavor)) {

try {

String clipboardContent =
(String)tran.getTransferData(DataFlavor.stringFlavor);

return clipboardContent;

} catch (UnsupportedFlavorException ex) {
ex.printStackTrace();
} catch (IOException ex) {
ex.printStackTrace();
}
}
return null;
}
/**
* @param args the command line arguments
*/
//public static void main(String[] args) {
//	System.out.println(displayTextFromClipboard());
//}

} 