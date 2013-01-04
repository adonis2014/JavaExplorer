package name.chenyuelin.entity.test;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the tree_table database table.
 */
@Entity
@Table(name = "tree_table")
public class TreeTable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2850088952940989920L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private byte id;

	@Lob()
	private String content;

	// bi-directional many-to-one association to TreeTable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private TreeTable treeTable;

	// bi-directional many-to-one association to TreeTable
	@OneToMany(mappedBy = "treeTable")
	private List<TreeTable> treeTables;

	public TreeTable() {
	}

	public byte getId() {
		return this.id;
	}

	public void setId(byte id) {
		this.id = id;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public TreeTable getTreeTable() {
		return this.treeTable;
	}

	public void setTreeTable(TreeTable treeTable) {
		this.treeTable = treeTable;
	}

	public List<TreeTable> getTreeTables() {
		return this.treeTables;
	}

	public void setTreeTables(List<TreeTable> treeTables) {
		this.treeTables = treeTables;
	}

}