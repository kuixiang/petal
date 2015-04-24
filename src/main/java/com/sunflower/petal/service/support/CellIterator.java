package com.sunflower.petal.service.support;

import jxl.Cell;
import jxl.Sheet;

import java.util.Iterator;

/**
 * Created by xiangkui on 14-2-22.
 */
public class CellIterator implements Iterator<Cell> {
	public enum IteratorModel {
		HORIZON, VERTICAL
	}

	private Sheet sheet;
	private IteratorModel model;// 水平、垂直迭代模式
	private int index;// 观察列
	private int start;
	private int end;
	private int position;

	public CellIterator(Sheet sheet, IteratorModel model, int viewIndex,
			int start, int end) {
		this.sheet = sheet;
		this.model = model;
		this.index = viewIndex;
		this.start = start;
		this.end = end;
		this.position = this.start - 1;
	}

	public boolean hasNext() {
		if (position >= end)
			return false;
		else
			return true;
	}

	public Cell next() {
		if (!hasNext()) {
			throw new IllegalStateException(" has no more cell to iterate");
		}
		if (model == IteratorModel.HORIZON) {
			return sheet.getCell(++position, index);
		} else if (model == IteratorModel.VERTICAL) {
			return sheet.getCell(index, ++position);
		} else {
			throw new IllegalStateException("Unkown iterator model");
		}
	}

	public void remove() {
		throw new UnsupportedOperationException("can not remove cell");
	}
}
