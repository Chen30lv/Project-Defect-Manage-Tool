import * as vscode from 'vscode';

const scripts = [
  {
    script: '缺陷1',
    inf:'出现在文件1:主要是由代码格式不规范引起的'
  },
  {
    script: '缺陷2',
    inf:'出现在文件2:主要是由函数引用引起的'
  },
  {
    script: '缺陷3',
    inf:'出现在文件3:主要是由变量命名重复引起的'
  },
  {
    script: '缺陷4',
    inf:'出现在文件4:主要是由文件路径引起的'
  }
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

// Tree Data Provider for BeeHive Command View
export class SideBarBeeHiveCommand implements vscode.TreeDataProvider<SideBarEntryItem> {
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
          command: 'DM-PackAnalysis.submitChild', // Command ID
          title: scripts[index].script,
          arguments: [scripts[index].script], // Arguments passed to the command
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
  const sidebarBeeHiveCommand = new SideBarBeeHiveCommand();
  const sidebarBeeHivePackageAnalysis = new SideBarBeeHivePackageAnalysis();
  vscode.window.registerTreeDataProvider(
    'DM-PackAnalysis',
    sidebarBeeHiveCommand
  );
    vscode.window.registerTreeDataProvider(
      'DM-General',
      sidebarBeeHivePackageAnalysis
    );


  // Register Commands
  vscode.commands.registerCommand('DM-PackAnalysis.submitChild', (args) => {
    const items = ['已经修改完成', '还未修改'];
  
    vscode.window.showQuickPick(items, {
      placeHolder: 'Select an option',
      canPickMany: false
    }).then((selectedItem) => {
      if (selectedItem) {
        vscode.window.showInformationMessage(`${args}: ${selectedItem}`);
      }
    });
  });

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