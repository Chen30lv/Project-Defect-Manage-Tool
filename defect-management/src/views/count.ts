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
  constructor(private version: string, public readonly label: string, public readonly collapsibleState: vscode.TreeItemCollapsibleState
  ) {
    super(label, collapsibleState);
    this.tooltip = `${this.label}-${this.version}`;
    this.description = `${this.version}-${Math.ceil(Math.random() * 1000)}`;
  }
}

// Tree Data Provider for BeeHive Command View
export class SideBarBeeHiveCommand implements vscode.TreeDataProvider<SideBarEntryItem> {

  private _onDidChangeTreeData: vscode.EventEmitter<SideBarEntryItem | undefined | null | void> = new vscode.EventEmitter<SideBarEntryItem | undefined | null | void>();
  readonly onDidChangeTreeData: vscode.Event<SideBarEntryItem | undefined | null | void> = this._onDidChangeTreeData.event;
  refresh(): void {
    this._onDidChangeTreeData.fire();
  }

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
          command: 'DM-General.openChild', // Command ID
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

// Tree Data Provider for BeeHive Package Analysis View
export class SideBarBeeHivePackageAnalysis implements vscode.TreeDataProvider<SideBarEntryItem> {

  private _onDidChangeTreeData: vscode.EventEmitter<SideBarEntryItem | undefined | null | void> = new vscode.EventEmitter<SideBarEntryItem | undefined | null | void>();
  readonly onDidChangeTreeData: vscode.Event<SideBarEntryItem | undefined | null | void> = this._onDidChangeTreeData.event;
  refresh(): void {
    this._onDidChangeTreeData.fire();
  }

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
  const sidebarBeeHiveCommand = new SideBarBeeHiveCommand();
  const sidebarBeeHivePackageAnalysis = new SideBarBeeHivePackageAnalysis();
  vscode.window.registerTreeDataProvider(
    'DM-Count',
    sidebarBeeHiveCommand
  );
 
  vscode.commands.registerCommand('DM-Count.refresh', () => {
    sidebarBeeHiveCommand.refresh();
    sidebarBeeHivePackageAnalysis.refresh();
  }
  );
};