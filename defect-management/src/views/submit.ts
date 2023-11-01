import * as vscode from 'vscode';
import * as path from 'path';

export function registerCommands(context: vscode.ExtensionContext) {
  let disposable = vscode.commands.registerCommand('dashboard.submit', () => {
    vscode.window.showInputBox({
        prompt: '请输入备注',
    }).then((remark) => {
        if (remark) {
            const openDocuments = vscode.workspace.textDocuments;

            if (openDocuments.length > 0) {
              const fileItems: vscode.QuickPickItem[] = openDocuments.map(doc => {
                return {
                    label: path.basename(doc.uri.fsPath),
                    description: doc.uri.fsPath,
                };
            });
            
                vscode.window.showQuickPick(fileItems, {
                    canPickMany: true,
                    placeHolder: '选择文件',
                }).then((selectedItems) => {
                    if (selectedItems) {
                        const selectedFiles = selectedItems.map((item) => item.description);
                        vscode.window.showInformationMessage('您选择的文件是: ' + selectedFiles.join(', '));
                    }
                });
            } else {
                vscode.window.showInformationMessage('没有打开的文件');
            }
        }
    });
});

context.subscriptions.push(disposable);
}

module.exports = function (context: vscode.ExtensionContext) {
  registerCommands(context);
};