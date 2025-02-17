/**
 * @id java/examples/emptyblock
 * @kind problem
 * @problem.severity error
 * @name Empty blocks
 * @description Finds empty block statements
 * @tags empty
 *       block
 *       statement
 */

import java

from BlockStmt blk
where blk.getNumStmt() = 0
select blk, "Empty block statement"
