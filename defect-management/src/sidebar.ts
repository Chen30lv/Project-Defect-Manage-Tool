import * as vscode from 'vscode';

export class SidebarProvider {
    // 创建 WebviewPanel
    private _panel?: vscode.WebviewPanel;

    // 定义构造函数
    constructor(private readonly _context: vscode.ExtensionContext) {}

    // 定义命令处理函数
    public showSidebar() {
        // 创建或显示现有的 WebviewPanel
        if (!this._panel) {
            this.createSidebar();
        } else {
            this._panel.reveal();
        }
    }

    // 创建 WebviewPanel
    private createSidebar() {
        // 创建 WebviewPanel
        this._panel = vscode.window.createWebviewPanel(
            'customSidebar',
            'Custom Sidebar',
            vscode.ViewColumn.Beside,
            {
                // 允许运行脚本
                enableScripts: true
            }
        );

        // 设置 Webview 内容
        this._panel.webview.html = this.getWebviewContent();

        // 监听面板关闭事件
        this._panel.onDidDispose(() => {
            this._panel = undefined;
        });
    }

    // 获取 Webview 内容
    private getWebviewContent() {
        // 这里可以编写 HTML 和 JavaScript 代码，用于自定义你的侧边栏视图
        const content = `
            <!DOCTYPE html>
            <html>
            <head>
                <title>Custom Sidebar</title>
            </head>
            <body>
                <p>Here</p>
            </body>
            </html>
        `;

        return content;
    }
}
