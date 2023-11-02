import * as vscode from 'vscode';

const scripts = [
  {
    script: 'webpack:dev',
  },
  {
    script: 'webpack:prod',
  },
  {
    script: 'server:dev',
  },
  {
    script: 'server:test',
  },
  {
    script: 'server:test-1',
  },
  {
    script: 'server:test-2',
  },
];

// Custom Tree Item Class
class SideBarEntryItem extends vscode.TreeItem {
  constructor(
    private version: string, 
    public readonly label: string,
    public readonly collapsibleState: vscode.TreeItemCollapsibleState
  ) {
    super(label, collapsibleState);
    this.tooltip = `${this.label}-${this.version}`;
    this.description = `${this.version}-${Math.ceil(Math.random() * 1000)}`;
    this.contextValue = 'scriptItem';
  }
}



// Tree Data Provider for BeeHive Package Analysis View
export class SideBarBeeHivePackageAnalysis implements vscode.TreeDataProvider<SideBarEntryItem> {
  constructor(private workspaceRoot?: string) {}

  getTreeItem(element: SideBarEntryItem): vscode.TreeItem {
    return element;
  }

  getChildren(element?: SideBarEntryItem): vscode.ProviderResult<SideBarEntryItem[]> {
    if (element) {
      // Children nodes
      var childrenList = [];
      for (let index = 0; index < scripts.length; index++) {
        var item = new SideBarEntryItem('1.0.0', scripts[index].script, vscode.TreeItemCollapsibleState.None);
        item.command = {
          command: 'DM-PackAnalysis.openChild', // Command ID
          title: scripts[index].script,
          arguments: [index], // Arguments passed to the command
        };
        childrenList[index] = item;
      }
      return childrenList;
    } else {
      // Root node
      return [
        new SideBarEntryItem('1.0.0', 'Button Group', vscode.TreeItemCollapsibleState.Collapsed),
      ];
    }
  }
}

module.exports = function (context: vscode.ExtensionContext) {
  // Register Sidebar Panels
  const sidebarBeeHivePackageAnalysis = new SideBarBeeHivePackageAnalysis();
  vscode.window.registerTreeDataProvider(
    'DM-PackAnalysis',
    sidebarBeeHivePackageAnalysis
  );

 
  
  vscode.commands.registerCommand(
    'DM-PackAnalysis.openChild',
    (args) => {
        // 创建 Webview
        const panel = vscode.window.createWebviewPanel(
            'customPopup', // 唯一标识符
            'Custom Popup', // 标题
            vscode.ViewColumn.Beside, // 显示位置
            {
                enableScripts: true // 允许在 Webview 中运行脚本
            }
        );
        // 加载自定义的 HTML 内容
        panel.webview.html = `
            <html>
            <head>
                <style>
                    /* 自定义样式 */
                    body {
                        margin: 0;
                        padding: 20px;
                    }
                </style>
            </head>
            <body>
                <h1>${args}</h1> <!-- 显示传入的参数 -->
            </body>
            </html>
        `;
    },
);

};
  


