// The module 'vscode' contains the VS Code extensibility API
// Import the module and reference it with the alias vscode in your code below
import * as vscode from 'vscode';
import { httpClientPromise } from './utils/initialization';
import { SideBarGeneric } from './views/dashboard';


let sidebarPanel: vscode.WebviewPanel | undefined = undefined;

async function postData() {
	try {
	  const httpClient = await httpClientPromise;
  
	  // 使用httpClient进行POST请求
	  const response = await httpClient.post('/api/defectInfo/search/MyDefectInfoVOList', {
		// 你的POST数据
	  });
  
	  console.log('Response:', response.data);
	} catch (error) {
	  console.error('Error during data posting:', error);
	}
  }


// This method is called when your extension is activated
// Your extension is activated the very first time the command is executed
export function activate(context: vscode.ExtensionContext) {

	// Use the console to output diagnostic information (console.log) and errors (console.error)
	// This line of code will only be executed once when your extension is activated
	console.log('Congratulations, your extension "defect-management" is now active!');
	//require('./sidebar')(context);
	//require('./views/count')(context);
	//postData()
	require('./views/dashboard');
	
	require('./views/list')(context);
	require('./views/submit')(context);
	require('./utils/initialization')(context);
	
	// The command has been defined in the package.json file
	// Now provide the implementation of the command with registerCommand
	// The commandId parameter must match the command field in package.json
	context.subscriptions.push(
        vscode.commands.registerCommand('dashboard.refresh', () => {
            SideBarGeneric
        })
    );
}

// This method is called when your extension is deactivated
export function deactivate() {
    
}
